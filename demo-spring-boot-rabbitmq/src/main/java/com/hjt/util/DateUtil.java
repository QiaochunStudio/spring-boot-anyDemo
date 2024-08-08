package com.hjt.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: hjt
 * @Date: Created in  2024-08-07
 * @desc:
 **/
public class DateUtil {

    public static String dateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
}
