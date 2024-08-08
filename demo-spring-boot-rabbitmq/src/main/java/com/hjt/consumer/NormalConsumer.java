package com.hjt.consumer;


import com.hjt.config.DeadLetterQueueConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/***
 * 创建死信消费队列
 */
@Component
public class NormalConsumer {

    @RabbitListener(queues = DeadLetterQueueConfig.NORMAL_QUEUE)
    public void consume(String msg, Channel channel, Message message) throws IOException {
        System.out.println("接收到normal-queue队列的消息："+msg);
        //消息被消费者拒绝（通过basic.reject 或者 back.nack），并且设置 requeue=false。
        channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        /**
         * channel.basicNack(deliveryTag, multiple, requeue) 是 RabbitMQ 的 Java 客户端中用于拒绝（Nack）一条或多条消息的方法。下面是对该方法的参数进行解释：
         *      deliveryTag：消息的交付标签（delivery tag），用于唯一标识一条消息。通过 message.getMessageProperties().getDeliveryTag() 获取消息的交付标签。
         *      multiple：是否拒绝多条消息。如果设置为 true，则表示拒绝交付标签小于或等于 deliveryTag 的所有消息；如果设置为 false，则只拒绝交付标签等于 deliveryTag 的消息。
         *      requeue：是否重新入队列。如果设置为 true，则被拒绝的消息会重新放回原始队列中等待重新投递；如果设置为 false，则被拒绝的消息会被丢弃。
         */
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
    }



}
