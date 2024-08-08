package com.hjt.producer;

import cn.hutool.core.date.DateUtil;
import com.hjt.config.DeadLetterQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/***
 * 死信队列生成者发送端
 */
@Component
@Slf4j
public class NormalProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void publishDead(){
        rabbitTemplate.convertAndSend(DeadLetterQueueConfig.NORMAL_EXCHANGE,"normal.topic","今天晚上有什么安排？");
        System.out.println("消息发送成功");
    }
}
