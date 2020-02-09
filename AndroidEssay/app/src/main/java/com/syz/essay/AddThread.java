package com.syz.essay;

import java.util.List;

public class AddThread implements Runnable{

    private List<Double> list;

    public AddThread(List<Double> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; ++i) {
            //ystem.out.println(i);
            list.add(Math.random());
        }
        System.out.println(list.toString());
    }
}
