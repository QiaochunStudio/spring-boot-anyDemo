package com.hjt.hutool.threadpool;

import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPoolDemo {
    private static ExecutorService pool = ExecutorBuilder.create()
            .setCorePoolSize(10)//初始池大小
            .setMaxPoolSize(20) //最大池大小
            .setWorkQueue(new LinkedBlockingQueue<>(100))//最大等待数为100
            .setThreadFactory(ThreadFactoryBuilder.create().setNamePrefix("IM-Pool-").build())// 线程池命名
            .build();

    public static void main(String[] args) {
//        for(int i = 0;i<16;i++){
//            pool.submit(new Thread(new MyRunDemo()));
//        }
        //自己测一下自己机器的核数
        System.out.println(Runtime.getRuntime().availableProcessors());


    }
}
