package com.hjt.consumer;

import cn.hutool.core.date.DateUtil;
import com.hjt.config.DelayedConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DelayConsumer {

    @RabbitListener(queues = DelayedConfig.DELAYED_QUEUE)
    public void delayConsumer(String msg, Channel channel, Message message){
        //消息被消费者拒绝（通过basic.reject 或者 back.nack），并且设置 requeue=false。
        try {
            log.error("延迟队列消费的时间{}", DateUtil.now());
            log.error("我是延迟消费,消息为{}",msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
