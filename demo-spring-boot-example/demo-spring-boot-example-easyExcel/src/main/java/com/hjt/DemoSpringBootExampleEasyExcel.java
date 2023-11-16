package com.hjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hjt
 * @date:2022/9/4
 */
@EnableScheduling //开启定时任务
@SpringBootApplication
public class DemoSpringBootExampleEasyExcel {
    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootExampleEasyExcel.class, args);
        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    EasyExcel模块启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" );
    }
}
