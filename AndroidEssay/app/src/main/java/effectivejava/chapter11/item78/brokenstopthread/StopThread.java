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

    /*虚拟机将下面代码：

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