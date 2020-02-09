package com.syz.essay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {

    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) {
//        List<Double> list = new ArrayList<>();
//        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
//        es.execute(new AddThread(list));
//        es.execute(new AddThread(list));
//        es.shutdown();


        int b[] = {0,0,0,0,0,0,0};
        int i;
        //在第i个位置上写1
        for (i = 5; i < 40; i += 10) {
            b[i / 32] |= (1 << (i % 32));
            //System.out.print((1 << (i % 32))+"___");
            //System.out.print(b[i / 32]+"___");
        }
        //输出整个bitset
        for (i = 0; i < 40; i++) {
            if (((b[i / 32] >> (i % 32)) & 1) != 0)
                System.out.print("1");
            else
                System.out.print("0");
        }
    }
}
