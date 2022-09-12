package com.hjt.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: redisson 配置类
 * @Author: hjt
 * @Date: 2021/07/14
 */
@Configuration
public class RedissonConfiguration {

    private static final String prefix = "redis://";

    @Bean
    @ConditionalOnMissingBean
    public RedissonProperties redissonProperties() {
        return new RedissonProperties();
    }

    /**
     * //TODO 单机模式配置
     **/
    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties) {
        if (redissonProperties.getAddressUrl() != null || redissonProperties.getMaster().getAddressUrl() != null || redissonProperties.getSlave().getAddressUrl() != null) {
            Config config = new Config();
            config.useSingleServer()
                    .setAddress(prefix + redissonProperties.getAddressUrl()).setPassword(redissonProperties.getPassword())
                    .setConnectTimeout(redissonProperties.getReconnectionTimeout())
                    .setRetryInterval(redissonProperties.getRetryInterval())
                    .setTimeout(redissonProperties.getTimeout())
                    .setConnectTimeout(redissonProperties.getConnectTimeout());
            return Redisson.create(config);
        }
        return null;
    }

    /**
     * //TODO 主从模式
     **/
    /*@Bean
    public RedissonClient getRedisson(RedissonProperties redissonProperties) {
        if (redissonProperties.getAddressUrl() == null || redissonProperties.getMaster().getAddressUrl() == null || redissonProperties.getSlave().getAddressUrl() == null) {
            return null;
        }
        RedissonClient redisson;
        Config config = new Config();
        config.useMasterSlaveServers()
                //可以用"rediss://"来启用SSL连接
                .setMasterAddress(prefix + redissonProperties.getMaster().getAddressUrl()).setPassword(redissonProperties.getMaster().getPassword())
                .addSlaveAddress(prefix + redissonProperties.getSlave().getAddressUrl())
                .setReconnectionTimeout(redissonProperties.getReconnectionTimeout())
                .setRetryInterval(redissonProperties.getRetryInterval())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectTimeout(redissonProperties.getConnectTimeout());//（连接超时，单位：毫秒 默认值：3000）;

        //  哨兵模式config.useSentinelServers().setMasterName("master").setPassword(password).addSentinelAddress("***(哨兵IP):26379", "***(哨兵IP):26379", "***(哨兵IP):26380");
        redisson = Redisson.create(config);
        return redisson;
    }*/
}
