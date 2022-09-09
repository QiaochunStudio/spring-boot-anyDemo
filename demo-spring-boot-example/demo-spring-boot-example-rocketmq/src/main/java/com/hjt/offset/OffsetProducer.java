package com.hjt.offset;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * 延时消息队列
 *
 * @author hjt
 * @date 2019/8/21
 */

@Component
@Slf4j
public class OffsetProducer {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send() {
        String message = "offset message:  ";
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.syncSendOrderly("topic-offset", message + i, "offset_orderly_key");
        }
    }

    /***
     * hjt写的延时消费demo
     */
    public void sendByHjt() throws Exception {
        Message message = new Message();
        //生产者
        DefaultMQProducer producer = new DefaultMQProducer("topic-offset-by-hjt-product");
        producer.setNamesrvAddr("192.168.1.219:9876");
        producer.start();
        for(int i = 0;i<5;i++){
            message.setTopic("topic-offset-by-hjt");
            message.setBody(("我是延迟消费啊啊" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            message.setDelayTimeLevel(4);
            producer.send(message);
        }
        //关闭生产者
        producer.shutdown();
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

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = ("我是延迟消费啊啊").getBytes(RemotingHelper.DEFAULT_CHARSET);
        String result = result(bytes);
        System.out.println("输出：" + result);
    }


}
