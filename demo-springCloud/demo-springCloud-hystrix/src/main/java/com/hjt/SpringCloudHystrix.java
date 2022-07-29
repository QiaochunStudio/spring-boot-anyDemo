package com.hjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/***
 * @author hjt
 * date by 2022-7-26
 */
//在启动类上添加@EnableCircuitBreaker来开启Hystrix的断路器功能
@EnableCircuitBreaker
@SpringBootApplication
public class SpringCloudHystrix {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrix.class, args);
    }
}
