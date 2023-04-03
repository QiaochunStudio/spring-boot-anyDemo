package com.hjt.javaDemo.collection.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author :hjt
 * @date : 2022/9/22
 */
public class HashMapTest {
    public static void main(String[] args) {
        // 创建 HashMap 对象 Sites
        HashMap<Integer, String> Sites = new HashMap<Integer, String>();
        // 添加键值对
        Sites.put(1, "Google");
        Sites.put(2, "Runoob");
        Sites.put(5, "Taobao");
        Sites.put(4, "Zhihu");
        // 输出 key 和 value
        //第一种遍历方法
        for (Integer i : Sites.keySet()) {
            System.out.println("key: " + i + " value: " + Sites.get(i));
        }
        // 返回所有 value 值
        for(String value: Sites.values()) {
            // 输出每一个value
            System.out.print(value + ", ");
        }


        //第二种遍历方法
        Iterator<Map.Entry<Integer, String>> entries = Sites.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, String> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }


}
