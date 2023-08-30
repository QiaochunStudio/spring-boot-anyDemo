package com.hjt.javaDemo.collection;

import java.util.HashSet;
import java.util.Iterator;

/***
 * 集合类demo
 */
public class CollectionDemo  {
    public static void main(String[] args) {
        for(int i = 0;i<4;i++){
            if(i==1){
                System.out.println("11");
                continue;
            }
            if(i==2){
                System.out.println("22");
            }
            if(i==3){
                System.out.println("33");
            }
            System.out.println("此时i=1会不会进到这里");
        }
    }
}
