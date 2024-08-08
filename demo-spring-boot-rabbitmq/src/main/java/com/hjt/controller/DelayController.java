package com.hjt.controller;

import com.hjt.producer.DelayProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/***
 * 延迟队列测试
 */
@RestController
@RequestMapping("/delay")
public class DelayController {
    @Resource
    private DelayProducer delayProducer;

    @GetMapping("/test")
    public void test(){
        delayProducer.publishDelay();
    }
}
