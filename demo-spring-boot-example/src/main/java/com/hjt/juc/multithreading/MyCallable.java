package com.hjt.juc.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable {
    public static void main(String[] args) throws Exception {

        MyThread1 myThread1 = new MyThread1();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread1());
        new Thread(futureTask).start();//开启线程
        System.out.println(futureTask.get());//获取返回值

    }
}


class  MyThread1 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int count = 0;
        for (int i = 1;i<100;i++){
            if (i%3==0){
                count++;
            }
        }
        return count;
    }
}
