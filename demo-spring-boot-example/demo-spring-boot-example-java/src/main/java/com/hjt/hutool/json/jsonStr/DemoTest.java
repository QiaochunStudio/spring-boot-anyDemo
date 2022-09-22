package com.hjt.hutool.json.jsonStr;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author :hjt
 * @date : 2022/9/21
 */
public class DemoTest {
    public static void main(String[] args) {

        String html = "{\"userId\":\"1\",\"title\":\"eqaaa\",\"speedLevel\":6}";
        JSONObject jsonObject = JSONUtil.parseObj(html);
        String userId = jsonObject.getStr("userId");
        System.out.println("userId:"+userId);
    }
}
