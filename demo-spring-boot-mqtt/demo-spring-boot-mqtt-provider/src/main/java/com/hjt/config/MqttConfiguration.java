package com.hjt.config;

import com.hjt.client.MqttPushClient;
import com.hjt.client.MqttSubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * mqtt配置类，获取mqtt连接
 */
@Component
@Configuration
@ConfigurationProperties(MqttConfiguration.PREFIX)
public class MqttConfiguration {

    @Autowired
    private MqttPushClient mqttPushClient;
    //指定配置文件application-local.properties中的属性名前缀
    public static final String PREFIX = "smkj.mqtt";
    /***
     * 主机ip
     */
    private String host;

    /***
     * 客户端id
     */
    private String clientId;

    /***
     * 用户名称
     */
    private String userName;

    /***
     * 密码
     */
    private String password;

    /***
     * 主题
     */
    private String topic;
    /***
     * 超时时间
     */
    private int timeout;

    /***
     * 心跳时间
     */
    private int keepAlive;

    /***
     * 需要回调的topic
     */
    private String[] topics ;

    /***
     * 回调top的qos
     */
    private int[] qos ;

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public int[] getQos() {
        return qos;
    }

    public void setQos(int[] qos) {
        this.qos = qos;
    }

    public String getClientid() {
        return clientId;
    }

    public void setClientid(String clientid) {
        this.clientId = clientid;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepAlive;
    }

    public void setKeepalive(int keepalive) {
        this.keepAlive = keepalive;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 连接至mqtt服务器，获取mqtt连接
     *
     * @return
     */
    @Bean
    public MqttPushClient getMqttPushClient() {
        //连接至mqtt服务器，获取mqtt连接
        mqttPushClient.connect(host, clientId, userName, password, timeout, keepAlive);
        //一连接mqtt,就订阅默认需要订阅的主题（如test_queue）
        new MqttSubClient(topics,qos);
        return mqttPushClient;
    }
}