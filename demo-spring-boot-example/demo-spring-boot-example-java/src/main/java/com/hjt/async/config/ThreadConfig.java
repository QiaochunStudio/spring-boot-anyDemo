package com.hjt.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能描述：异步自定义线程池
 *
 * @author hjt
 * @date 2023/4/26-10:25
 */
@Configuration
public class ThreadConfig {
    //交给spring管理,起名,可以配置多个线程池
    @Bean(name = "pool1")
    public Executor createExecutor1(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);//核心线程池数(默认线程池);
        threadPoolTaskExecutor.setMaxPoolSize(5);//最大线程池数
        threadPoolTaskExecutor.setKeepAliveSeconds(60);//允许线程空闲时间(单位:默认为秒)
        threadPoolTaskExecutor.setQueueCapacity(200);//缓冲队列数
        threadPoolTaskExecutor.setThreadNamePrefix("hjtThread1");//线程池名前缀
        //线程池对拒绝任务的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskExecutor;
    }

    //交给spring管理,起名,可以配置多个线程池
    @Bean(name = "pool2")
    public ThreadPoolTaskExecutor createExecutor2(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);//核心线程池数(默认线程池);
        threadPoolTaskExecutor.setMaxPoolSize(5);//最大线程池数
        threadPoolTaskExecutor.setKeepAliveSeconds(60);//允许线程空闲时间(单位:默认为秒)
        threadPoolTaskExecutor.setQueueCapacity(200);//缓冲队列数
        threadPoolTaskExecutor.setThreadNamePrefix("hjtThread2");//线程池名前缀
        //线程池对拒绝任务的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return threadPoolTaskExecutor;
    }
}
