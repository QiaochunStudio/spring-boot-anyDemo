package com.hjt.offset;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.hjt.message.Demo;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.apache.rocketmq.common.consumer.ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

/***
 * 延时消费
 */
@Component
public class OffsetConsumerByHjt {
    private static final Logger log = LoggerFactory.getLogger(OffsetConsumerByHjt.class);

    @Component
    @RocketMQMessageListener(topic = "topic-offset-by-hjt", consumerGroup = "topic-offset-by-hjt-consumer")
    public class OfferConsumerBy implements RocketMQListener<Message>, RocketMQPushConsumerLifecycleListener {
        @Override
        public void onMessage(Message message) {
            System.out.println("哈哈哈哈我进来消费");
            String result = result(message.getBody());
            System.out.println("输出 result "+result);
            log.info("topic-offset-by-hjt: {}", new String(message.getBody()));

            //强制转换


            //如果是数组
            JSONArray jsonArray = JSONUtil.parseArray(result);
            List<Demo> list = JSONUtil.toList(jsonArray, Demo.class);

            for(int i = 0;i<list.size();i++){
                Demo demo = list.get(i);
                System.out.println("demo:"+demo);
            }


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
