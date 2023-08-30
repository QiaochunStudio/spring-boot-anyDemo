package com.hjt.hutool.http;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;

public class Demo {
    private static final String url ="localhost:9102/notice/api/v1/t-message-notice/websocketList";



    public static void main(String[] args) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id","1");
        String result2 = HttpRequest.get(url)
                .form(paramMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println(result2);

        HttpResponseDataVo price = JSONUtil.toBean(result2, HttpResponseDataVo.class);
        System.out.println(price);




    }
}
