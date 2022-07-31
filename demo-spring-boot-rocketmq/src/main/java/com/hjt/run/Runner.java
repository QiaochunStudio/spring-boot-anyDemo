package com.hjt.run;


import com.hjt.messagemodel.MessageModelProducer;
import com.hjt.offset.OffsetProducer;
import com.hjt.order.OrderProducer;
import com.hjt.simple.RocketmqProducer;
import com.hjt.tags.TagProducer;
import com.hjt.transaction.TransactionProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author hjt
 * @date 2019/8/21
 */
@Component

public class Runner implements CommandLineRunner {

    @Resource
    private RocketmqProducer rocketmqProducer;
    @Resource
    private TransactionProducer transactionProducer;
    @Resource
    private OrderProducer orderProducer;
    @Resource
    private TagProducer tagProducer;
    @Resource
    private OffsetProducer offsetProducer;
    @Resource
    private MessageModelProducer messageModelProducer;

    @Override
    public void run(String... args) throws Exception {
        //同步
//        rocketmqProducer.sync();
        //单向发送
//        rocketmqProducer.oneWay();
        //异步
//        rocketmqProducer.async();
        //事务处理
//        transactionProducer.produce();
        //订单  顺序消息
//        orderProducer.sendSyncOrderly();
        //顺序消息测这个 1 2 3 4
//        orderProducer.testSendSyncOrderly1();
//        orderProducer.testSendSyncOrderly2();
//        orderProducer.testSendSyncOrderly3();
//        orderProducer.testSendSyncOrderly4();
        //事务消息
//        tagProducer.sendTagsMessage();
        //延迟消息
        offsetProducer.send();
//        messageModelProducer.send();
    }


}
