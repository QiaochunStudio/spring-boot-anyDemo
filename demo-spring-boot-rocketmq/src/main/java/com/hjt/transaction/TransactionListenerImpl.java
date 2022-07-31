package com.hjt.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author hjt
 * @date 2019/8/20
 */
@Slf4j
//@RocketMQTransactionListener(txProducerGroup = "tx-group")  2.0.3的版本有这个
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {

        // 模拟本地事务不通过
        log.info("============== executeLocalTransaction");



        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        // 模拟回查本地事务
        log.info("============== checkLocalTransaction");


        return RocketMQLocalTransactionState.COMMIT;
    }
}
