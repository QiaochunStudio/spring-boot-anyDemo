package com.hjt.javaDemo.dateDemo;

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
   public static void main(String[] args) {
      String dateStr1 = "2023-03-01 20:34:23";
      Date date1 = DateUtil.parse(dateStr1);

      String dateStr2 = "2023-03-01 23:33:23";
      Date date2 = DateUtil.parse(dateStr2);
      long betweenDay = DateUtil.between(date1, date2, DateUnit.HOUR);

      System.out.println("输出："+betweenDay);
   }
}
