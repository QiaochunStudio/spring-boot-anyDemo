package com.hjt.simple;

import com.hjt.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @author hjt
 * @date 2019/6/6
 */
@Component
@Slf4j
public class RocketmqProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    //同步发送
    public void sync() {
        Message<String> message = new Message<>();
        message.setId(UUID.randomUUID().toString());
        message.setContent("Hello, springboot-ac-rocketmq !");
        rocketMQTemplate.convertAndSend("topic-queue-one", message);
        rocketMQTemplate.convertAndSend("topic-queue-two", "Hello, springboot-ac-rocketmq !");
    }


    public void async() {
        Message<String> message = new Message<>();
        message.setId(UUID.randomUUID().toString());
        message.setContent("Hello,I am asyncSend !");
        rocketMQTemplate.asyncSend("async-one", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send successful");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("send fail; {}", throwable.getMessage());
            }
        });
    }
    public void oneWay() {
        rocketMQTemplate.sendOneWay("topic-oneWay", "send one-way message");
    }



}
