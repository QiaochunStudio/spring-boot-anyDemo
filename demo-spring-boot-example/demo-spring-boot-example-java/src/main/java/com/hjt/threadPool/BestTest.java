package com.hjt.threadPool;

import java.util.concurrent.ConcurrentHashMap;

public class BestTest implements Runnable {

    private ConcurrentHashMap<Long, Long> data ;
    private Long index;

    public BestTest(Long index){
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("输出data:"+index);


    }
}
