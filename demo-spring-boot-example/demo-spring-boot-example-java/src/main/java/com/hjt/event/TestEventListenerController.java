package com.hjt.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEventListenerController {

    @Autowired
    private MyTestEventPubLisher publisher;

    @GetMapping(value = "/test/testPublishEvent1")
    public void testPublishEvent(){
        publisher.pushListener("我来了！");
    }
}

