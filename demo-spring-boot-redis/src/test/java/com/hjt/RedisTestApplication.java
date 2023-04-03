package com.hjt;

import com.hjt.geo.GeoUtil;
import com.hjt.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
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

    @Resource
    private GeoUtil geoUtil;

    @Test
    public void testRedis(){
        redisService.setCacheObject("test",22,120L, TimeUnit.SECONDS);
    }

    @Test
    public void testRedisGeo(){
        geoUtil.geoAdd("北京西站", 116.328103,39.900835);
        geoUtil.geoAdd("北京南站", 116.385488,39.87128);
        geoUtil.geoAdd("北京西站-南广场", 116.327766,39.898944);
        geoUtil.geoAdd("北京西站-南进站口", 116.327765,39.899347);
        geoUtil.geoAdd("中铁设计大厦", 116.328628,39.896485);
        geoUtil.geoAdd("瑞海大厦", 116.326661,39.903778);
        // 计算北京南站与北京西站之间的距离
        double distance = geoUtil.distanceBetween("北京西站-南广场", "北京南站");
// 5898.4001
        if(distance<2000){
            System.out.println("小于2KM打卡");
        }
        else{
            System.out.println("大于2KM打卡");
        }
        System.out.println(distance);


// 查询距离北京西站5000米范围内的地方
        Map<String, Double> distanceInclude = geoUtil.distanceInclude("北京西站", 5000);
        System.out.println(distanceInclude);

    }

}
