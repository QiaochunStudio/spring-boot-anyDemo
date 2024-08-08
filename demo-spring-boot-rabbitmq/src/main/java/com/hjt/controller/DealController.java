package com.hjt.controller;

import com.hjt.producer.NormalProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dead")
public class DealController {
    @Resource
    private NormalProducer normalProducer;

    @GetMapping(value = "/test")
    public void test(){
        normalProducer.publishDead();
    }
}
