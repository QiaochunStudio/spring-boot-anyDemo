package com.hjt.offset;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 延时消息队列
 * @author hjt
 * @date 2019/8/21
 */

@Component
@Slf4j
public class OffsetProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String message = "offset message:  ";
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSendOrderly("topic-offset", message + i, "offset_orderly_key");
        }
    }

}
