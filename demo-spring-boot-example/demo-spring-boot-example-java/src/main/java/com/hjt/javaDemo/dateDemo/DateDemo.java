package com.hjt.javaDemo.dateDemo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hjt
 * @date:2023/3/29
 */
public class DateDemo  {
   public static Long count = 0L;
   public static void main(String[] args) {
//      String dateStr1 = "2023-03-01 20:34:23";
//      Date date1 = DateUtil.parse(dateStr1);
//
//      String dateStr2 = "2023-03-01 23:33:23";
//      Date date2 = DateUtil.parse(dateStr2);
//      long betweenDay = DateUtil.between(date1, date2, DateUnit.HOUR);
//
//      System.out.println("输出："+betweenDay);

//      while (true){
//         for(Long i = count;i<10;i++){
//            count++;
//            if(i==5){
//               count=0L;
//               System.out.println("进来后:"+count);
//            }
//            System.out.println("没进来前:"+count);
//         }
//      }

//      Date date1 = new Date();
//      String formatDate = DateUtil.format(date1, "HH:mm:ss");
//      System.out.println(formatDate);
//
//      DateTime newDate3 = DateUtil.offsetHour(date1, 10);
//      System.out.println(newDate3);
//      String formatDate1 = DateUtil.format(newDate3, "HH:mm:ss");
//      System.out.println(formatDate1);

//      Integer aa = Double.valueOf("5.22").intValue();
//      System.out.println(aa);

      long timestampInMillis = 1684563783000L; // 例如：2021-08-30 00:00:00

      String formattedString = DateUtil.date(timestampInMillis).toString();

      System.out.println(formattedString);

//      System.out.println("111"+DateUtil.offsetHour(new Date(), -1));
   }
}
