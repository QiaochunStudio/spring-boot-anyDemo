package com.hjt.consumer;

import com.alibaba.fastjson.JSON;
import com.hjt.annotation.RowListener;
import com.hjt.event.CustomEvent;
import org.springframework.stereotype.Component;

@Component
public class DbRowListener {

    @RowListener(tableNames = {"zhiri"},dsName = "test")
    public void execute(String event){
        CustomEvent customEvent = JSON.parseObject(event, CustomEvent.class);
        System.out.println(JSON.toJSONString(customEvent));
    }



}
