package effectivejava.chapter11.item78.fixedstopthread1;

import java.util.concurrent.*;

public class StopThread {
    private static boolean stopRequested;

    private static synchronized void requestStop() {
        stopRequested = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args)
            throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested())
                i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
        requestStop();
    }





    /*
     * 注意，写方法(requestStop)和读方法(stop- required)都是同步的。
     * 仅同步写方法是不够的！除非读和写操作同步，否则不能保证同步工作。
     * 有时，只同步写(或读)的程序可能在某些机器上显示有效，但在这种情况下，表面的现象是具有欺骗性的。
     * 即使没有同步，StopThread中同步方法的操作也是原子性的。换句话说，
     * 这些方法上的同步仅用于其通信效果，而不是互斥。虽然在循环的每个迭代上同步的成本很小，
     * 但是有一种正确的替代方法，它不那么冗长，而且性能可能更好。如果stoprequest声明为volatile，
     * 则可以省略StopThread的第二个版本中的锁定。
     *
     * */
}  