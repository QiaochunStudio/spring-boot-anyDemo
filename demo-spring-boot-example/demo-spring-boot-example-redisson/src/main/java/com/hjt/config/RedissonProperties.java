package com.hjt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("spring.redis.redisson")
public class RedissonProperties {
    /**
     * 地址
     **/
    private String addressUrl;

    /**
     * 密码
     **/
    private String password;

    /*
     * 主机
     **/
    private Master master = new Master();

    /*
     * 备机
     **/
    private Slave slave = new Slave();

    /**
     * 重新连接超时
     **/
    private int reconnectionTimeout = 10000;

    /**
     * 重试间隔
     **/
    private int retryInterval = 5000;

    /**
     * 超时
     **/
    private int timeout = 10000;

    /**
     * 请求消息最大长度
     **/
    private int connectTimeout = 10000;

    public static class Master {
        private String addressUrl;
        private String password;

        public String getAddressUrl() {
            return addressUrl;
        }

        public void setAddressUrl(String addressUrl) {
            this.addressUrl = addressUrl;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class Slave {
        private String addressUrl;
        private String password;

        public String getAddressUrl() {
            return addressUrl;
        }

        public void setAddressUrl(String addressUrl) {
            this.addressUrl = addressUrl;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Slave getSlave() {
        return slave;
    }

    public void setSlave(Slave slave) {
        this.slave = slave;
    }

    public int getReconnectionTimeout() {
        return reconnectionTimeout;
    }

    public void setReconnectionTimeout(int reconnectionTimeout) {
        this.reconnectionTimeout = reconnectionTimeout;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }
}

