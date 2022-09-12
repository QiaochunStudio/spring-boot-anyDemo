package com.hjt.stringDemo;

/**
 * @author hjt
 * @date:2022/9/12
 */
public class testDemo {

    public static void main(String[] args) {
        String uri = "redis://1.15.180.135:6379";
        String ipV6Host = uri.substring(uri.indexOf("://") + 3, uri.lastIndexOf(":"));
//        String ipV6Host = uri.substring(uri.indexOf("://")+3);
        System.out.println("lastIndex:"+uri.lastIndexOf(":"));
        System.out.println("ipV6:"+ipV6Host);

        int i = uri.indexOf("://");
        System.out.println("i："+i);
    }
}
