package com.hjt.order;

import com.hjt.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author hjt
 * @date 2019/8/21
 */
@Component
public class OrderProducer {

    private static final Integer NUM = 3;

    @Resource
    private RocketMQTemplate rocketMQTemplate;


    private final String id1 = "10086";
    private final String id2 = "10087";


    public void sendSyncOrderly() {
        String message = "orderly message:";
        for (int i = 0; i < NUM; i++) {
            // 模拟有序消费
            rocketMQTemplate.syncSendOrderly("topic-orderly", message + i, "select_queue_key2");
            rocketMQTemplate.syncSendOrderly("topic-orderly", message + i, "select_queue_key1");
            rocketMQTemplate.syncSendOrderly("topic-orderly", message + i, "select_queue_key2");
            rocketMQTemplate.syncSendOrderly("topic-orderly", message + i, "select_queue_key3");
            rocketMQTemplate.syncSendOrderly("topic-orderly", message + i, "select_queue_key4");
            // 模拟无序消费
//            rocketMQTemplate.syncSend("topic-orderly", message + i);
        }
    }

    /***
     * hashKey为订单id
     */
    public void testSendSyncOrderly1() {
        Message<String> stringMessage = new Message<>();
        stringMessage.setId(id1);
        String message = "10";
        stringMessage.setContent(message);
        // 模拟有序消费
        rocketMQTemplate.syncSendOrderly("topic-orderly", stringMessage, id1);
    }

    /***
     * hashKey为订单id
     */
    public void testSendSyncOrderly2() {
        Message<String> stringMessage = new Message<>();
        stringMessage.setId(id2);
        String message = "20";
        stringMessage.setContent(message);
        // 模拟有序消费
        rocketMQTemplate.syncSendOrderly("topic-orderly", stringMessage, id2);
    }

    /***
     * hashKey为订单id
     */
    public void testSendSyncOrderly3() {
        Message<String> stringMessage = new Message<>();
        stringMessage.setId(id1);
        String message = "30";
        stringMessage.setContent(message);
        // 模拟有序消费
        rocketMQTemplate.syncSendOrderly("topic-orderly", stringMessage, id1);
    }

    /***
     * hashKey为订单id
     */
    public void testSendSyncOrderly4() {
        Message<String> stringMessage = new Message<>();
        stringMessage.setId(id2);
        String message = "40";
        stringMessage.setContent(message);
        // 模拟有序消费
        rocketMQTemplate.syncSendOrderly("topic-orderly", stringMessage, id2);
    }


}
