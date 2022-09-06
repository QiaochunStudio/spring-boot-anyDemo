package com.hjt.stringDemo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author :hjt
 * @date : 2022/9/5
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String s = "我.爱.中.国".replaceAll("\\+","%20");

        String strName = URLEncoder.encode("会员列表", "UTF-8");
        System.out.println("输出strName:"+strName);
        String s1 = strName.replaceAll("\\+", "%20");
        String decode = URLDecoder.decode(s1);
        System.out.println("输出："+s1);
    }
}
