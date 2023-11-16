package com.hjt.javaDemo.bigDecimal;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalTest {
    /***
     * 比较大小
     */
    public static void testNumber(){
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(5);

        if (a.compareTo(b) == 0)
            System.out.println("a = b");

        if (a.compareTo(b) == -1)
            System.out.println("a < b");

        if (a.compareTo(b) == 1)
            System.out.println("a > b");

        if (a.compareTo(b) != 0)
            System.out.println("a != b");

        if (a.compareTo(b) != -1)
            System.out.println("a >= b");

        if (a.compareTo(b) != 1)
            System.out.println("a <= b");
    }
    public static void main(String[] args) {
//        BigDecimal str1=new BigDecimal("0.9234567890123456789");
//        BigDecimal str2=new BigDecimal("0.1234567890123456789");
//
//        /***
//         * add(BigDecimal)	加
//         * subtract(BigDecimal)	减
//         * multiply(BigDecimal)	乘
//         * divide(BigDecimal ,int,RoundingMode）	除，3个参数，分别表示除数，保留小数点，小数点处理方式
//         */
//        System.out.println(str1.add(str2));
//        System.out.println(str1.subtract(str2));
//        System.out.println(str1.multiply(str2));
//
//        /**除法  保留两位小数点*/
//        str1.divide(str2,2,RoundingMode.HALF_UP);
//
//        BigDecimal multiply = new BigDecimal("3").divide(new BigDecimal(1)).multiply(new BigDecimal("4"));
//        System.out.println("测试 除法："+multiply);
//
//        /***
//         * 模式	说明	保留2位例子
//         * RoundingMode.UP	向上取整 类似 （ceil）	1.051→1.06
//         * RoundingMode.HALF_UP	四舍五进 (常用)	1.054→1.05；1.055→1.06
//         * RoundingMode.DOWN	向下取整 类似（floor）	1.059→1.05
//         * RoundingMode.HALF_DOWN	五舍六进	1.055→1.05；1.056→1.06
//         */
//        //保留两位小数点
//        BigDecimal bigDecimal=new BigDecimal("1.057");
//        System.out.println(bigDecimal.setScale(2, RoundingMode.HALF_UP));//1.06
//
//        BigDecimal bigDecimal1= BigDecimal.ZERO;
//        for(int i = 0;i<3;i++){
//            bigDecimal1 = bigDecimal1.add(new BigDecimal(i));
//        }
//        System.out.println(bigDecimal1);
//
//
//        BigDecimal bigDecimal2 = new BigDecimal("3");
//        BigDecimal bigDecimal3 = new BigDecimal("3");
//        BigDecimal subtract = bigDecimal2.subtract(bigDecimal3);
//        System.out.println(subtract);
//
//        //变为负数
//        BigDecimal bigDecimal4 = new BigDecimal("-20");
//        System.out.println(bigDecimal4);
//        BigDecimal bigDecimal5 = new BigDecimal("60").negate();
//        System.out.println(bigDecimal5);
//        testNumber();

        BigDecimal round = NumberUtil.round("80.256987", 1);
        BigDecimal round1 = new BigDecimal(80);
        if(round.compareTo(round1)==1){
            System.out.println("大于80");
        }
        else{
            System.out.println("小于80");
        }




    }


}
