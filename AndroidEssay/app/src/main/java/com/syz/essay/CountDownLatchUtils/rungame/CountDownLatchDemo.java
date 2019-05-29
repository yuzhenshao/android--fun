package com.syz.essay.CountDownLatchUtils.rungame;

/**
 CountDownLatch类是一个同步计数器,构造时传入int参数,该参数就是计数器的初始值，每调用一次countDown()方法，计数器减1,计数器大于0 时，await()方法会阻塞程序继续执行
 CountDownLatch如其所写，是一个倒计数的锁存器，当计数减至0时触发特定的事件。利用这种特性，可以让主线程等待子线程的结束。下面以一个模拟运动员比赛的例子加以说明。
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    private static final int PLAYER_AMOUNT = 5;
    public CountDownLatchDemo() {
        // TODO Auto-generated constructor stub
    }

//    public static void main(String[] args) {
//        // begin为了确保每个运动员都准备就绪了才能开始比赛，
//        // 所以每个运动员都会持有一个begin，并且调用begin.await()方法进入等待。
//        CountDownLatch begin = new CountDownLatch(1);
//        // 对于整个比赛，所有运动员结束后才算结束。主线程会持有end，
//        // 确保所有的运动员都到终点才能宣布比赛结束。同时每个运动员到达终点的时候都会调用，end.countdown()方法。
//        CountDownLatch end = new CountDownLatch(PLAYER_AMOUNT);
//        Player[] plays = new Player[PLAYER_AMOUNT];
//
//        for(int i=0;i<PLAYER_AMOUNT;i++)
//            plays[i] = new Player(i+1,begin,end);
//
//        //设置特定的线程池，大小为5
//        ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
//        for(Player p:plays)
//            //分配线程，每个运动员都准备就绪，但是没有开始赛跑，因为他们持有的begin都调用了begin.await()
//            exe.execute(p);
//        System.out.println("Race begins!");//宣布比赛开始
//        //所有运动员开始赛跑，begin的参数减少1，变为0，持有begin.await的线程开始运行。
//        begin.countDown();
//        try{
//            //等待所有的运动员都到达终点，比赛结束。
//            end.await();
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally{
//            System.out.println("Race ends!");//宣布比赛结束
//        }
//        exe.shutdown();
//    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(10000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }.start();

        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
