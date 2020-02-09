package effectivejava.chapter11.item78.brokenstopthread;

import java.util.concurrent.*;

// Broken! - How long would you expect this program to run?  (Page 312)
public class StopThread {
    private static boolean stopRequested;

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


    /*你可能希望这个程序运行大约一秒钟，之后主线程将stoprequired设置为true，从而导致后台线程的循环终止。
    然而，在我的机器上，程序永远不会终止：后台线程永远循环!问题是在没有同步的情况下，无法确保后台线程何时
    （如果有的话）看到主线程所做的stopRequested值的变化：*/


    /*在没有同步的情况下，虚拟机将下面代码：

            while (!stopRequested)
            i++;
    转换成这样：

            if (!stopRequested)
            while (true)
            i++;
    这种优化称为提升(hoisting，它正是OpenJDK Server VM所做的。 结果是活泼失败( liveness failure)：程序无法取得进展。
    解决问题的一种方法是同步对stopRequested属性的访问。
    */
}