package com.hjt.hutool.number;

import cn.hutool.core.util.NumberUtil;

/**
 * 功能描述：
 *
 * @author hjt
 * @date 2023/4/24-17:42
 */
public class TestDemo {


    public static void main(String[] args) {
        String s = "1630833580480012290";
        String s1 = "http-nio-80-exec-15";
        String s2 = "SPXX_UPLOAD_EXECUTOR-";
        System.out.println(NumberUtil.isNumber(s));
        System.out.println(NumberUtil.isNumber(s1));
        System.out.println(NumberUtil.isNumber(s2));
    }
}
