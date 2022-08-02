package com.hjt.tags;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author hjt
 * @date 2019/8/21
 */
@Component
@Slf4j
@RocketMQMessageListener(
        topic = "topic-tags",
        consumerGroup = "tags-consumer-group",
        selectorExpression = "A||C")
public class TagConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.println("messgaetag:"+message);
        log.info("======={}=======", message);
    }
}
