package com.hjt.stringDemo;

/**
 * @author :hjt
 * @date : 2022/10/19
 */
public class StringDemo {
    public static void main(String[] args) {
        String cc = new String("ab");
        String dd = new String("ab");
        System.out.println("equals:"+cc.equals(dd));
        System.out.println("==："+cc==dd);
    }
}
