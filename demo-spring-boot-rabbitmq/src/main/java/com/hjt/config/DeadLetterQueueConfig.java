package com.hjt.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 死信队列和普通队列配置
 */
@Configuration
public class DeadLetterQueueConfig {

    // 普通的交换机和队列
    public static final String NORMAL_EXCHANGE = "normal-exchange";
    public static final String NORMAL_QUEUE = "normal-queue";
    public static final String NORMAL_ROUTING_KEY = "normal.#";

    //死信队列和交换机
    public static final String DEAD_EXCHANGE = "dead-exchange";
    public static final String DEAD_QUEUE = "dead-queue";
    public static final String DEAD_ROUTING_KEY = "dead.#";

    /**
     * 创建普通的交换机
     * @return
     */
    @Bean
    public Exchange normalExchange(){
        return ExchangeBuilder.topicExchange(NORMAL_EXCHANGE).build();
    }

    /**
     * 创建普通的队列，然后绑定死信队列和交换机
     * @return
     */
    @Bean
    public Queue normalQueue(){
        //普通队列这里需要绑定死信交换机
        return QueueBuilder
                .durable(NORMAL_QUEUE)
                .deadLetterExchange(DEAD_EXCHANGE) //死信交换机
                .deadLetterRoutingKey("dead.hello") //绑定的routing_key
                .build();
    }

    /**
     * 绑定交换机和队列
     * @param normalQueue 队列
     * @param normalExchange 交换机
     * @return
     */
    @Bean
    public Binding normalBinding(Queue normalQueue,Exchange normalExchange){
        return BindingBuilder.bind(normalQueue).to(normalExchange).with(NORMAL_ROUTING_KEY).noargs();
    }

    /**
     * 创建死信交换机
     * @return
     */
    @Bean
    public Exchange deadExchange(){
        return ExchangeBuilder.topicExchange(DEAD_EXCHANGE).build();
    }

    /**
     * 创建死信队列
     * @return
     */
    @Bean
    public Queue deadQueue(){
        return QueueBuilder.durable(DEAD_QUEUE).build();
    }

    /**
     * 死信队列绑定死信交换机,并且设置死信topic
     * @param deadQueue 队列
     * @param deadExchange 交换机
     * @return
     */
    @Bean
    public Binding deadBinding(Queue deadQueue,Exchange deadExchange){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_ROUTING_KEY).noargs();
    }
}
