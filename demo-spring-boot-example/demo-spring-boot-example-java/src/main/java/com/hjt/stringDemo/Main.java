package com.hjt.stringDemo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author :hjt
 * @date : 2022/9/5
 */
public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        String s = "我.爱.中.国".replaceAll("\\+","%20");

//        String strName = URLEncoder.encode("会员列表", "UTF-8");
//        System.out.println("输出strName:"+strName);
//        String s1 = strName.replaceAll("\\+", "%20");
//        String decode = URLDecoder.decode(s1);
//        System.out.println("输出："+s1);

//
//        String Str = new String("W3Cschool教程:www.voidme.com");
//        String SubStr1 = new String("youj");
//        String SubStr2 = new String("com");
//
//        System.out.print("查找字符 o 最后出现的位置 :" );
//        System.out.println(Str.lastIndexOf( 'o' ));
//        System.out.print("从第14个位置查找字符 o 最后出现的位置 :" );
//        System.out.println(Str.lastIndexOf( 'o', 14 ));
//        System.out.print("子字符串 SubStr1 最后出现的位置:" );
//        System.out.println( Str.lastIndexOf( SubStr1 ));
//        System.out.print("从第十五个位置开始搜索子字符串 SubStr1最后出现的位置 :" );
//        System.out.println( Str.lastIndexOf( SubStr1, 15 ));
//        System.out.print("子字符串 SubStr2 最后出现的位置 :" );
//        System.out.println(Str.lastIndexOf( SubStr2 ));


//        String s = "6,7,";
//        String[] split = s.split(",");
//        for(int i = 0;i<split.length;i++){
//            System.out.println("222:"+split[i]);
//        }
        System.out.println(new Date().toString());


    }
}
