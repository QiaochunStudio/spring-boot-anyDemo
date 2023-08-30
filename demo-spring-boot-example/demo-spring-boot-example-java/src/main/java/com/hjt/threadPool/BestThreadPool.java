package com.hjt.threadPool;


import java.util.concurrent.*;

public class BestThreadPool  {
    public static void main(String[] args) {
        //阻塞队列
        LinkedBlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(5);
        //实现`RejectedExecutionHandler`接口来自定义策略。
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        //定义遍历
//        ConcurrentHashMap<Long, Long> data = new ConcurrentHashMap<>(10);

        /***
         * 核心池数为5
         * 最大核心池数为10
         * 非核心线程闲置下来最多存活的时间 60s
         * 线程池任务队列queue
         * 异常处理handler
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 15,
                60, TimeUnit.SECONDS, queue,handler);

        long startTime = System.currentTimeMillis();
        for(Long i = 761091L;i<=848857L;i++){
            new BestTest(i);
//            threadPool.execute(new Thread(new BestTest(i)));
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println(endTime1-startTime);



    }
}
