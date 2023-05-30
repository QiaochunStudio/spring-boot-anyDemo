package com.hjt.async.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 功能描述：
 * @
 *
 * @author hjt
 * @date 2023/4/26-9:53
 */
@Component
public class AsyncComponent {
    @Async("pool2")
    public void testAsync(){
        for(int i =0;i<5;i++){
            try {
                System.out.println("哈哈哈 我是异步");
            }catch (Exception e){
//                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t"+Thread.currentThread().getId()+"\t"+new Date()+"\t"+"执行了多线程任务"+i);
        }
    }
}
