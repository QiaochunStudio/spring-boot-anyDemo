package com.hjt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author :hjt
 * @date : 2023/1/31
 */
@Configuration
@MapperScan({"com.hjt.mapper"})
public class MyBatisConfig {
}
