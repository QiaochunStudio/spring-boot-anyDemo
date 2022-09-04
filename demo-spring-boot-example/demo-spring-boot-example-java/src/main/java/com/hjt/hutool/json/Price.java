package com.hjt.hutool.json;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.util.List;
@Data
public class Price {
    private List<List<ADT>> ADT;

    public static void main(String[] args) {
        String json = "{\"ADT\":[[{\"BookingCode\":[\"N\",\"N\"]}]]}";
        Price price = JSONUtil.toBean(json, Price.class);
        System.out.println("p:"+price);
    }
}
