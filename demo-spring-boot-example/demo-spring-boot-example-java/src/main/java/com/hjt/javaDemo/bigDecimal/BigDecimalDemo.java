package com.hjt.javaDemo.bigDecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * @author :hjt
 * @date : 2022/9/13
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("0");
        BigDecimal bigDecimal2 = new BigDecimal(1.222222222);
        //输出 1.22222222200000008029974196688272058963775634765625
        System.out.println(bigDecimal2);


        //double需要BigDecimal的时候
        BigDecimal bigDecimal = BigDecimal.valueOf(1.22222222);
        BigDecimal bigDecimal3 = new BigDecimal("1.22222222");
        //输出都是：1.22222222
        System.out.println("bigDecimal："+bigDecimal);

        //加减乘除
        BigDecimal first = new BigDecimal("36.123");
        BigDecimal second = new BigDecimal("12.123");

        ArrayList<String> objects = new ArrayList<>();
        objects.add("1");
        objects.add("1");
        objects.add("1");
        objects.add("1");
        objects.add("1");
        objects.add("1");
        final BigDecimal[] test = {new BigDecimal(BigInteger.ZERO)};
        objects.stream().forEach(temp ->{
            BigDecimal third = new BigDecimal("36.123");
            test[0] = test[0].add(third);
        });
        System.out.println("输出："+test[0]);


        //初始化总数
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        for(int i = 1;i<5;i++){
            BigDecimal third = new BigDecimal("36.123");
            total = total.add(third);
        }
        System.out.println("total:"+total);

    }
}
