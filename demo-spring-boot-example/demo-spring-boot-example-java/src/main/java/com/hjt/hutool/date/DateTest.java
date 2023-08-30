package com.hjt.hutool.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author :hjt
 * @date : 2022/9/20
 */
public class DateTest {
    public static void main(String[] args) {

        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
        System.out.println(today);

        DateTime parse = DateUtil.parse(today);
        String format = DateUtil.format(parse, "yyyyMMdd");
        System.out.println("format:"+format);



        Date date = new Date();
        String dateFormat = DateUtil.format(date, "yyyyMMdd");
        System.out.println("dateFormat:"+format);

        DateTime parse1 = DateUtil.parse("2023-08-05 00:00:30");
        String formatDate = DateUtil.format(parse1, "HH:mm:ss");
        System.out.println("输出:"+formatDate);






    }
}
