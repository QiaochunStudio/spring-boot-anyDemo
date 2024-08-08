package com.hjt.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DelayedConfig {
    public static final String DELAYED_EXCHANGE = "delayed-exchange";
    public static final String DELAYED_QUEUE = "delayed-queue";
    public static final String DELAYED_ROUTING_KEY = "delayed.#";

    /**
     * 创建一个延迟交换机（Exchange）并返回该交换机对象。
     * @return
     */
    @Bean
    public Exchange delayedExchange(){
        //创建了一个HashMap对象，用于存储交换机的属性。然后，将一个名为x-delayed-type的属性和值为"topic"的键值对添加到HashMap中。
        HashMap<String, Object> map = new HashMap<>();
        map.put("x-delayed-type","topic");

        /**
         * 使用CustomExchange类创建一个自定义交换机对象。CustomExchange是Spring AMQP库提供的一个类，用于创建自定义的交换机。构造方法的参数依次为交换机的名称、类型、是否持久化、是否自动删除和属性。
         * ，交换机的名称为DELAYED_EXCHANGE，类型为"x-delayed-message"，持久化为true，自动删除为false，属性为之前创建的HashMap对象。
         */
        return new CustomExchange(DELAYED_EXCHANGE,"x-delayed-message",true,false,map);
    }

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue delayedQueue(){
        return QueueBuilder.durable(DELAYED_QUEUE).build();
    }

    /**
     * 绑定交换机和队列
     * @param delayedQueue
     * @param delayedExchange
     * @return
     */
    @Bean
    public Binding delayedBinding(Queue delayedQueue,Exchange delayedExchange){
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
