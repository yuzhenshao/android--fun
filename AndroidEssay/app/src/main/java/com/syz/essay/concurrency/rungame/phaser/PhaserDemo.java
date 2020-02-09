package com.syz.essay.concurrency.rungame.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    private static final int PLAYER_AMOUNT = 5;
    // 模拟了100米赛跑，10名选手，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        ////构造方法，与CountDownLatch一样，传入同步的线程数
        final Phaser phaser=new Phaser(1) ;

        for (int index = 0; index < PLAYER_AMOUNT; index++) {
            phaser.register();//动态添加参与者
            new Thread(new PhaserPlayer(phaser),"player"+index).start();
        }
        System.out.println("Game Start");
        //注销当前线程,比赛开始
        //动态撤销线程在phaser的注册，通知phaser对象，该线程已经结束该阶段且不参与后面阶段
        phaser.arriveAndDeregister();
        //当phaser没有参与同步的线程时（或者onAdvance返回true），phaser是终止态（如果phaser进入终止态
        // arriveAndAwaitAdvance()和awaitAdvance()都会立即返回，不在等待）isTerminated返回true。
        while(!phaser.isTerminated()){
            System.out.println("phaser.isTerminated()"+phaser.isTerminated());
            Thread.sleep(5000);

        }
        System.out.println("phaser.isTerminated()"+phaser.isTerminated());
        System.out.println("Game Over");
    }
}
