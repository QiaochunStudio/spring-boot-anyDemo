package com.hjt.timedemo;

import cn.hutool.Hutool;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class TimeDemo {
    public static int count = 0;

    public void zhiHuanXgTemp() throws InterruptedException {
        while (count <= 3) {
            for (int i = 0; i < 15; i++) {
                Thread.sleep(1000);
                String now = DateUtil.now();
                log.error("时间：" + now + "主线程名称:" + Thread.currentThread().getName() + "====输出{}", i);
            }
            count++;
        }
        log.warn("退出循环");


    }

    public static void testTime(String time) {
        DateDtlVo dtlVo = getDateDtl(time);
        String day = dtlVo.getBef() + " 00:00:00";
        System.out.println("day:"+day);
        String hour = dtlVo.getBef() + " " + dtlVo.getHour() + ":00:00";
        System.out.println("hour:"+hour);
    }

    /**
     * 获取一个日期的详情
     *
     * @param obj
     * @return
     */
    public static DateDtlVo getDateDtl(Object obj) {

        String date = getObjStr(obj);
        if (date.equals("")) {
            date = getCurDate(0);
        }

        if (!date.equals("")) {
            if (!date.contains(" ")) {
                date = date + " 00:00:00";
            }
        }

        DateDtlVo vo = new DateDtlVo();
        String bef = getDateLeft(date);
        String rgt = getDateRight(date);

        String year = bef.substring(0, bef.indexOf("-"));
        String month = bef.substring(bef.indexOf("-") + 1, bef.lastIndexOf("-"));
        String day = bef.substring(bef.lastIndexOf("-") + 1, bef.length());

        String hour = rgt.substring(0, rgt.indexOf(":"));
        String min = rgt.substring(rgt.indexOf(":") + 1, rgt.lastIndexOf(":"));
        String sec = rgt.substring(rgt.lastIndexOf(":") + 1, rgt.length());

        vo.setBef(bef);
        vo.setRgt(rgt);

        vo.setYear(year);
        vo.setMonth(month);
        vo.setDay(day);

        vo.setHour(hour);
        vo.setMin(min);
        vo.setSec(sec);

        return vo;

    }

    /**
     * 获取日期的左边方法
     *
     * @param obj
     * @return
     */
    public static String getDateLeft(Object obj) {
        String str = getObjStr(obj);
        return str.substring(0, str.lastIndexOf(" "));
    }

    /**
     * 获取日期的右边方法
     *
     * @param obj
     * @return
     */
    public static String getDateRight(Object obj) {
        String str = getObjStr(obj);
        return str.substring(str.lastIndexOf(" ") + 1);
    }


    /**
     * 全局获取当前日期时间并格式化
     *
     * @return
     */
    public static String getCurDate(int type) {
        String pat = "";
        if (type == 0) {
            pat = "yyyy-MM-dd HH:mm:ss";
        } else if (type == 1) {
            pat = "yyyy-MM-dd HH:mm:ss:SS";
        } else if (type == 2) {
            pat = "yyyy-MM-dd";
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pat);
        return sdf.format(date);


    }

    /**
     * obj转str
     *
     * @param obj
     * @return
     */
    public static String getObjStr(Object obj) {
        String str = "";
        String temp = String.valueOf(obj);
        if (obj == null) {

        } else {
            if (temp.equals("") || temp.equals("null") || temp.equals("undefined") || temp.equals("NaN")) {
            } else {
                str = temp;
            }
        }

        return str.trim();

    }


    public static void main(String[] args) {
        testTime("2023-11-08 09:06:01");
    }


}
