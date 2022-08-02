package com.hjt.tags;

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
public class TagProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendTagsMessage() {
        String[] tags = new String[]{"A", "B", "C", "D"};
        String message = "tags message :  ";
        for (int i = 0; i < tags.length; i++) {
            rocketMQTemplate.syncSend("topic-tags:" + tags[i], message + tags[i]);
        }
    }
}
