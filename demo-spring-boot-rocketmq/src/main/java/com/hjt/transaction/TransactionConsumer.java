package com.hjt.transaction;


import com.hjt.message.MessageTransaction;
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
public class TransactionConsumer implements RocketMQListener<MessageTransaction> {

    @Override
    public void onMessage(MessageTransaction message) {
        log.info("topic-tx received message: {}", message);
        log.info("消费端开始消费信息 执行B服务加100操作");
        //执行B服务加100的操作
        try{
            //B服务加100
            int addMoneyByB = 100;
        }
        //如果B服务加100失败,可是A已经减100成功了，这时候要把异常记录下来，人工进行处理
        catch (Exception e){
            log.error("B服务加100异常,需要人工处理,异常信息为：{}",e.getMessage());
            //用一张异常表单独记录  该消息的id 可以作为异常表的主键
            String id = message.getBId();
        }
    }

}
