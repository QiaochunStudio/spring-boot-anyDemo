package com.hjt.transaction;

import com.hjt.message.Message;
import com.hjt.message.MessageTransaction;
import com.hjt.transaction.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author hjt
 * @date 2019/8/20
 */
@Component
@Slf4j
public class TransactionProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private TransactionMapper transactionMapper;

    public void produce() {
        MessageTransaction<String> message = new MessageTransaction<>();
        //在正在的业务中 Aid和Bid应该是前端已经知道是啥，传给后端,比如A的userId和B的UserId
        message.setAId(UUID.randomUUID().toString());
        message.setBId(UUID.randomUUID().toString());
        message.setContent("B即将要+100元,A要减100元");
        log.info("========sending message=========:{}",message);
//        rocketMQTemplate.sendMessageInTransaction("tx-group", "topic-tx", MessageBuilder.withPayload(message).build(), null); 2.0.3有这个版本 tx-group
//        transactionMapper.insertData(message.getAId(),message.getContent());
        rocketMQTemplate.sendMessageInTransaction( "topic-tx", MessageBuilder.withPayload(message).build(), null);
        log.info("========finish send =========");
    }

}

