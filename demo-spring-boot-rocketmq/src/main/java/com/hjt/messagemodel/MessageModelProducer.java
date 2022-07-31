package com.hjt.messagemodel;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hjt
 * @date 2019/8/21
 */

@Component
@Slf4j
public class MessageModelProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String message = "message model message:  ";
        System.out.println("开始发送初始化消息");
        rocketMQTemplate.syncSend("topic-message-model", message);
    }


}
