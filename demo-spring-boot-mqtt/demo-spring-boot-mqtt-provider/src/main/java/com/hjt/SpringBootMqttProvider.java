package com.hjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMqttProvider {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMqttProvider.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    mqtt消费模块启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" );
    }
}
