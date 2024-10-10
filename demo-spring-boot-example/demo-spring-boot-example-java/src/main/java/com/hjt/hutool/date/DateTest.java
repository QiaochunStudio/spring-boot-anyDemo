package com.hjt.hutool.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author :hjt
 * @date : 2022/9/20
 */
public class DateTest {
    public static void test(){
        // 获取当前时间
        long currentTimeMillis = System.currentTimeMillis();

        // 获取目标时间，这里用一个示例时间
        long targetTimeMillis = DateUtil.parse("2023-11-07 09:00:00").getTime();

        // 定义20分钟的毫秒数
        long thirtyMinutesMillis = 20 * 60 * 1000;

        // 检查目标时间和当前时间是否相差30分钟以上
        if (Math.abs(targetTimeMillis - currentTimeMillis) >= thirtyMinutesMillis) {
            System.out.println("目标时间和当前时间相差20分钟以上");
        } else {
            System.out.println("目标时间和当前时间相差不足30分钟");
        }
    }
    public static void main(String[] args) {

        //当前日期字符串，格式：yyyy-MM-dd
//        String today= DateUtil.today();
//        System.out.println(today);
//
//        DateTime parse = DateUtil.parse(today);
//        String format = DateUtil.format(parse, "yyyyMMdd");
//        System.out.println("format:"+format);
//
//
//
//        Date date = new Date();
//        String dateFormat = DateUtil.format(date, "yyyyMMdd");
//        System.out.println("dateFormat:"+format);
//
//        DateTime parse1 = DateUtil.parse("2023-08-05 00:00:30");
//        String formatDate = DateUtil.format(parse1, "HH:mm:ss");
//        System.out.println("输出:"+formatDate);
//        test();





    }
}
