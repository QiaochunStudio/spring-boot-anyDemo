package com.hjt.threadPool;

import cn.hutool.core.thread.ThreadUtil;

/**
 * @author :hjt
 * @date : 2022/9/27
 */
public class HutoolSleep {

    public static void main(String[] args) {
        for(int i = 0;i<5;i++){
            System.out.println("输出:"+i);
            ThreadUtil.safeSleep(10*1000);

            if(i==2){

                break;
            }
        }
        System.out.println("我已经跳出来了");

//        new Thread(new MyRunnable()).start();
    }


}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("哈哈哈" + "运行  :  " + i);
            try {
                Thread.sleep(7000);
                if(i==3){
                    break;


                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
