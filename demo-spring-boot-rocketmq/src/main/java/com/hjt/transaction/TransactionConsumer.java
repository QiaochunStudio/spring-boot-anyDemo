package com.hjt.transaction;


import com.hjt.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author hjt
 * @date 2019/8/20
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "topic-tx", consumerGroup = "tx-consumer-group")
public class TransactionConsumer implements RocketMQListener<Message> {

    @Override
    public void onMessage(Message message) {
        log.info("topic-tx received message: {}", message);
    }

}
