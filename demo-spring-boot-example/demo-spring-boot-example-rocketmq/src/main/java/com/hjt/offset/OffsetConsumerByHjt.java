package com.hjt.offset;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static org.apache.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

/***
 * 延时消费
 */
@Component
@Slf4j
public class OffsetConsumerByHjt {

    @Component
    @RocketMQMessageListener(topic = "topic-offset-by-hjt", consumerGroup = "topic-offset-by-hjt-consumer")
    public class OfferConsumerBy implements RocketMQListener<Message>, RocketMQPushConsumerLifecycleListener {
        @Override
        public void onMessage(Message message) {
            System.out.println("哈哈哈哈我进来消费");
            String result = result(message.getBody());
            System.out.println("输出 result "+result);
            log.info("topic-offset-by-hjt: {}", new String(message.getBody()));
        }

        @Override
        public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
            //第一次启动从队列最后位置消费，后续再启动接着上次消费的进度开始消费
            defaultMQPushConsumer.setConsumeFromWhere(CONSUME_FROM_LAST_OFFSET);
        }
    }

    public static String result(byte[] decrypt) {
        try {
            String result = new String(decrypt, "UTF-8");
            return result;
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
