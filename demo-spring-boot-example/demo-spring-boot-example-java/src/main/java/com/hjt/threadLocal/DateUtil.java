package com.hjt.threadLocal;

/**
 * @author :hjt
 * @date : 2022/10/20
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    private static final SimpleDateFormat simpleDateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


    public static Date parse(String dateString) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /***
     * 增加了 ThreadLocal
     * @param dateString
     * @return
     */
    public static Date newParse(String dateString) {
        Date date = null;
        try {
            date = dateFormatThreadLocal.get().parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

