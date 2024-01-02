package com.hjt.hutool.threadpool;

public class MyRunDemo implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("我来了:"+name);
    }
}
