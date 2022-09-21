package com.hjt.stringDemo;

/**
 * @author hjt
 * @date:2022/9/12
 */
public class testDemo {

    public static void main(String[] args) {
//        String uri = "redis://116.205.224.23:6379";
//        String ipV6Host = uri.substring(uri.indexOf("://") + 3, uri.lastIndexOf(":"));
////        String ipV6Host = uri.substring(uri.indexOf("://")+3);
//        System.out.println("lastIndex:"+uri.lastIndexOf(":"));
//        System.out.println("ipV6:"+ipV6Host);
//
//        int i = uri.indexOf("://");
//        System.out.println("i："+i);


        String str = "http://localhost:4401/pay/api/v1/pay-info/pay?Authorization=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.YWRtaW4.VaJOloHfQLjacnm6-__pSaeNZ1JbLAdlgeJT3JEptos&orderId=769357409411153920";
                   String temp = "Authorization=";
        boolean authorization = str.contains(temp);
        System.out.println("输出:"+temp);
        int authorization1 = str.lastIndexOf(temp);
        System.out.println(authorization1);
        String substring = str.substring(authorization1+temp.length(), str.length());
        if(substring.contains("&")){
            int i = substring.lastIndexOf("&");
            System.out.println(substring.substring(0, i));
        }
        System.out.println(substring);


    }
}
