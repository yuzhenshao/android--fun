package com.syz.essay.concurrency.rungame.phaser;

import java.util.concurrent.Phaser;

public class PhaserPlayer implements Runnable {

    private final Phaser phaser;

    PhaserPlayer(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            // 第一阶段——等待创建好所有线程再开始
            //类似await()方法，记录到达线程数，阻塞等待其他线程到达同步点后再继续执行。
            System.out.println(Thread.currentThread().getName() + " threadarrived");
            phaser.arriveAndAwaitAdvance();

            // 第二阶段——等待所有选手准备好再开始
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + " ready");
            phaser.arriveAndAwaitAdvance();

            // 第三阶段——等待所有选手准备好到达，到达后，该线程从phaser中注销，不在进行下面的阶段。
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println(Thread.currentThread().getName() + " arrived");
            //动态撤销线程在phaser的注册，通知phaser对象，该线程已经结束该阶段且不参与后面阶段
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
