package com.hjt.hutool.json;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.Date;

@Data
public class UserA {
    private String name;
    private String a;
    private Date date;
//    private List<Seq> sqs;

    public static void main(String[] args) {
        UserA userA = new UserA();
        userA.setName("nameTest");
        userA.setDate(new Date());
//        userA.setSqs(CollectionUtil.newArrayList(new Seq(null), new Seq("seq2")));

// false表示不跳过空值
        JSONObject json = JSONUtil.parseObj(userA, false);
        Console.log(json.toStringPretty());
        System.out.println(json.toStringPretty());
        String s = json.toStringPretty();
        UserA userA1 = JSONUtil.toBean(s, UserA.class);
        System.out.println(userA);
    }
}