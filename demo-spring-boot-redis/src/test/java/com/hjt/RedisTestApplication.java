package com.hjt;

import com.hjt.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author :hjt
 * @date : 2023/1/31
 */
@SpringBootApplication
@SpringBootTest(classes = RedisTestApplication.class)
@RunWith(SpringRunner.class)
public class RedisTestApplication {
    @Resource
    private RedisService redisService;


    @Test
    public void testRedis(){
        redisService.setCacheObject("test",22,120L, TimeUnit.SECONDS);
    }

}
