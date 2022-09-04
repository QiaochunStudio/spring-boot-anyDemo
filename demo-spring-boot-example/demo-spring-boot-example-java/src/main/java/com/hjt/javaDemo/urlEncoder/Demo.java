package com.hjt.javaDemo.urlEncoder;

import java.io.UnsupportedEncodingException;

/**
 * @author hjt
 * @date:2022/9/4
 */
public class Demo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String name=java.net.URLEncoder.encode("测试", "UTF-8");
        System.out.println(name);
        name=java.net.URLEncoder.encode(name,"UTF-8");
        System.out.println(name);
        name=java.net.URLDecoder.decode(name, "UTF-8");
        System.out.println(name);
        System.out.println(java.net.URLDecoder.decode(name, "UTF-8"));
    }
}
