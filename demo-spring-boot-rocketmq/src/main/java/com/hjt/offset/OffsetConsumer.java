package com.hjt.offset;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * @author hjt
 * @date 2019/8/21
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "topic-offset",
        consumerGroup = "offset-consumer-group")
public class OffsetConsumer implements RocketMQListener<String>,RocketMQPushConsumerLifecycleListener{
    @Override
    public void onMessage(String message) {
        System.out.println("延迟队列消息："+message);
        log.info("======={}=======", message);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
//        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        //延迟2分钟 System.currentTimeMillis()-100000 换成时间戳就是比当前时间早2分钟
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()+1000));
    }
}
