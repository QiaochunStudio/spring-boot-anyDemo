package com.hjt.javaDemo.collection.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author :hjt
 * @date : 2022/9/22
 */
public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
        linkedHashMap.put(3, "order3");
        linkedHashMap.put(1, "order1");
        linkedHashMap.put(2, "order2");
        linkedHashMap.forEach((key, value) -> System.out.println(key + "-->" + value));

    }
}
