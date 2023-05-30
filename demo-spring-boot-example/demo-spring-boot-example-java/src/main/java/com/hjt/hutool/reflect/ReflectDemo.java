package com.hjt.hutool.reflect;

import cn.hutool.core.util.ReflectUtil;

/**
 * 功能描述：反射demo
 *
 * @author hjt
 * @date 2023/4/23-16:11
 */
public class ReflectDemo {

    public static void main(String[] args) {
        //获取某个类的所有方法
//        Method[] methods = ReflectUtil.getMethods(TestClass.class);
//        System.out.println("获取某个类的所有方法: "+methods);
//        //获取某个类的指定方法
//        Method method = ReflectUtil.getMethod(TestClass.class, "getA");
//        System.out.println("获取某个类的指定方法: "+methods);



        TestClass testClass = new TestClass();
        ReflectUtil.invoke(testClass, "setA", 10);
//        Object getA = ReflectUtil.invoke(testClass, "getA");
        Object setA = ReflectUtil.invoke(testClass, "getTest");
        System.out.println("输出:"+setA);




    }
}
