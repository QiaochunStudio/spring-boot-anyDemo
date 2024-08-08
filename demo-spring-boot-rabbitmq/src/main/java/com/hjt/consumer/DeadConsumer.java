package com.hjt.consumer;

import cn.hutool.core.date.DateUtil;
import com.hjt.config.DeadLetterQueueConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@Slf4j
public class DeadConsumer {

    @RabbitListener(queues = DeadLetterQueueConfig.DEAD_QUEUE)
    public void consume(String msg, Channel channel, Message message) throws IOException {
        System.out.println("我是死信队列："+msg);
        //消息被消费者拒绝（通过basic.reject 或者 back.nack），并且设置 requeue=false。
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);

    }
}
