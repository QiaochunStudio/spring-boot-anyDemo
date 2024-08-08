package com.hjt.producer;


import cn.hutool.core.date.DateUtil;
import com.hjt.config.DelayedConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class DelayProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void publishDelay() {
        rabbitTemplate.convertAndSend(DelayedConfig.DELAYED_EXCHANGE, "delayed.hello", "大江东去浪淘尽", new MessagePostProcessor() {
            //创建了一个匿名内部类实现了MessagePostProcessor接口，并重写了postProcessMessage()方法。在该方法中，设置了消息的延迟时间为50,000毫秒（即50秒）
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                log.error("延迟队列发送的时间{}", DateUtil.now());
//                //设置消息的延迟时间，单位为毫秒。
//                message.getMessageProperties().setDelay();
                // 使用x-delay头设置延迟时间（单位为毫秒）
                Map<String, Object> headers = message.getMessageProperties().getHeaders();
                headers.put("x-delay",5000);
                return message;
            }
        });
        System.out.println("消息发送成功");
    }


}
