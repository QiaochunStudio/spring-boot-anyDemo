package com.hjt.client;

import com.hjt.config.MqttConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author:Dong
 * @Date：2020/7/31 9:59
 */
@Slf4j
@Component
public class MqttSubClient {

//    @Resource
//    private MqttConfiguration mqttConfiguration;

    public MqttSubClient(String []topics,int qos[]) {
        subScribeDataPublishTopic(topics,qos);
    }


    private void subScribeDataPublishTopic(String []topics,int qos[]) {
        subscribe(topics,qos);
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic 主题名
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            MqttClient client = MqttPushClient.getClient();
            if (client == null) return;
            String topics[] = new String[2];
            int qosArrays[] = new int[2];
            topics[0] = "aabb";
            topics[1] = "test_queue";
            qosArrays[0] = 0;
            qosArrays[1] = 0;
//            client.subscribe(topic, qos);
            client.subscribe(topics, qosArrays);
            log.info("订阅主题:{}", topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    private void subscribe(String []topics,int qos[]) {
        try {
            MqttClient client = MqttPushClient.getClient();
            if(client==null){
                return;
            }
            client.subscribe(topics, qos);
            log.info("订阅主题:{}", topics);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
