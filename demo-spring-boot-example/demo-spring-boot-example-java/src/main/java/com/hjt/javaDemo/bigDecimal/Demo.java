package com.hjt.javaDemo.bigDecimal;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author :hjt
 * @date : 2022/9/29
 */
public class Demo {
    public static void main(String[] args) {
//        Integer aa = 1;
//        Integer bb = 1;
//        if(aa.equals(bb)){
//            System.out.println("222");
//        }
//        else {
//            System.out.println("333");
//        }
//        while (true){
//            for(int i = 0;i<3;i++){
//                System.out.println(i);
//            }
//        }


//
//            for (Long i = 694916L; i <= 848601L; i++) {
//                Date date = new Date();
//                SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
//                System.out.println(dateFormat.format(date));
//                boolean equals = dateFormat.format(date).equals("16:15:00");
//                if(equals){
//                    System.out.println(i+"输出当前时间: "+dateFormat.format(date));
//                    return;
//                }
//                else{
//                    System.out.println(i+"还没到时间"+dateFormat.format(date));
//                }
//            }

//        while (true){
//            String initDay = "2023-08-05";
//            Date date = new Date();
//            SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm:ss");
//            String format = dateFormat.format(date);
//            String selectDay = initDay + " " + format;
//            if(selectDay.equals("2023-08-05 17:30:25")){
//                System.out.println("========"+selectDay);
//                return;
//            }
//            else {
//                System.out.println("时间未到----:"+selectDay);
//            }
//        }
        Date date = new Date();
        String riqi = DateUtil.format(date, "yyyy-MM-dd");
        System.out.println(riqi);

        SimpleDateFormat dateFormat= new SimpleDateFormat("hh:mm:ss");
        String format = dateFormat.format(date);
        System.out.println(format);


//
//        Calendar cal= Calendar.getInstance();
//        int y=cal.get(Calendar.YEAR);
//        int m=cal.get(Calendar.MONTH);
//        int d=cal.get(Calendar.DATE);
//        int	h=cal.get(Calendar.HOUR_OF_DAY);
//        int	mi=cal.get(Calendar.MINUTE);
//        int s=cal.get(Calendar.SECOND);
//        System.out.println(y+" "+m+" "+d+" "+h+" "+mi+" "+s);


        // 获取当前日期和时间
//        DateTime currentTime = DateUtil.date();
//
//        // 创建目标日期和时间
//        DateTime startDate = DateUtil.parse("2023-08-05 00:00:00");
//        DateTime endDate = DateUtil.parse("2023-08-05 23:59:59");
//
//        // 比较当前时间与目标时间范围
//        if (currentTime.isAfter(startDate) && currentTime.isBefore(endDate)) {
//            System.out.println("当前时间在2023-08-04 00:00:00和2023-08-04 23:59:59之间。");
//        } else {
//            System.out.println("当前时间不在指定范围内。");
//        }

    }
}

