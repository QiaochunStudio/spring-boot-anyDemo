package com.hjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * @author hjt
 * 启动类
 */
@SpringBootApplication
public class SpringCloudSentinelService {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudSentinelService.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    Sentinel-service 启动成功     ヾ(◍°∇°◍)ﾉﾞ\n" );
    }
}
