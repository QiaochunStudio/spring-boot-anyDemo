package com.hjt.hutool.number;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;

public class DecimalRounder {
    public static void main(String[] args) {
//        double number = 3.15159265359;
        int decimalPlaces = 1;

        String str = "26.052846";

        BigDecimal rounded = NumberUtil.round(str, decimalPlaces);
        String s = rounded.toString();
//        System.out.println("原始数: " + number);
        System.out.println("四舍五入后: " + rounded);
        System.out.println("四舍五入后str: " + s);
        System.out.println("四舍五入后double: " + rounded.doubleValue());
    }
}

