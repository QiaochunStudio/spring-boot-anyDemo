package com.hjt.javaDemo.collection.hashmap;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * @author :hjt
 * @date : 2022/9/22
 */
public class TreeMapTest {
    public static void main(String[] args) {
//        TreeMap<String, String> treeMap = new TreeMap<String,String>();
//        treeMap.put("9", "9");
//        treeMap.put("8", "8");
//        treeMap.put("7", "7");
//        treeMap.put("6", "6");
//        treeMap.put("5", "5");
//        treeMap.put("4", "4");
//        treeMap.put("3", "3");

//        System.out.println("迭代器遍历");
//        Iterator<String> strItr = treeMap.keySet().iterator();
//        while(strItr.hasNext()){
//            System.out.println(treeMap.get(strItr.next()));
//        }


        TreeMap<String, String> treeMap = new TreeMap<String,String>();
        treeMap.put("9", "9");
        treeMap.put("8", "8");
        treeMap.put("7", "7");
        treeMap.put("6", "6");
        treeMap.put("5", "5");
        treeMap.put("4", "4");
        treeMap.put("3", "3");

        System.out.println("迭代器遍历");
        Iterator<String> strItr = treeMap.keySet().iterator();
        while(strItr.hasNext()){
            System.out.println(treeMap.get(strItr.next()));
        }


    }
}
