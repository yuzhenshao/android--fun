package effectivejava.chapter11.item78.fixedstopthread2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

// Cooperative thread termination with a volatile field
public class StopThread {
    //虽然volatile修饰符不执行互斥，但它保证任何读取属性的线程都会看到最近写入的值
    private static volatile boolean stopRequested;

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }


    /*
     * 在使用volatile时一定要小心。这里多说下时一定要小心的内容
     * */


    //单个可原子访问的属性，不需要同步来保护其不变量，但是，该方法将无法正常工作。
    private static volatile int nextSerialNumber = 0;

    public static int generateSerialNumber() {
        return nextSerialNumber++;
    }


//    修复generateSerialNumber的一种方法是将synchronized修饰符添加到其声明中。
//    这确保了多个调用不会交叉读取，并且每次调用该方法都会看到所有先前调用的效果。
//    完成后，可以并且应该从nextSerialNumber中删除volatile修饰符。

    //更好的是，遵循条目 59条中建议并使用AtomicLong类，它是java.util.concurrent.atomic包下的一部分。
    // 这个包为单个变量提供了无锁，线程安全编程的基本类型。 虽然volatile只提供同步的通信效果，
    // 但这个包还提供了原子性。 这正是我们想要的generateSerialNumber，它可能强于同步版本的代码：

    private static final AtomicLong nextSerialNum = new AtomicLong();

    public static long generateSerialNumber1() {
        return nextSerialNum.getAndIncrement();
    }


    /*
     * 补充：
     * nextSerialNumber被volatile修饰，保证了可见性，但是++并不是一个原子性操作，
     * 它被拆分为load、use、assign三步，而这三步在多线程环境中，use和assgin是多次出现的，
     * 但这操作是非原子性的，也就是在read和load之后，如果主内存count变量发生修改之后，
     * 线程工作内存中的值由于已经加载，不会产生对应的变化，也就是私有内存和公有内存中的变量不同步，
     * 所以计算出来的值和预期不一样，就产生了线程安全的问题，所以需要用synchronized进行加锁同步
     *
     *
     *
     * 所以volatile只能保证可见性不能保证原子性，但用volatile修饰long和double可以保证其操作原子性。
     * 对于64位的long和double，如果没有被volatile修饰，那么对其操作可以不是原子的。在操作的时候，可以分成两步，每次对32位操作。
     * 如果使用volatile修饰long和double，那么其读写都是原子操作
     * 对于64位的引用地址的读写，都是原子操作
     * 在实现JVM时，可以自由选择是否把读写long和double作为原子操作
     * 推荐JVM实现为原子操作
     * */
}
