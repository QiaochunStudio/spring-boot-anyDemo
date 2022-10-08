package com.hjt.suanfa.sort;

import java.util.Scanner;

/**
 * @author hjt
 * @date:2022/10/7
 */
public class QuickSort {
    public static void display(int a[]){
        for(int i = 0;i<a.length;i++){
            System.out.printf(a[i]+"  ");
        }
    }
    public static void main(String[] args) {
//        Long [] length = new Long[10];
//        for(int i = 0;i<length.length;i++){
//            Scanner scanner = new Scanner(System.in);
//            length[i] = scanner.nextLong();
//        }
        int[] aa = {12, 32, 5, 78};
        /*冒泡排序  从大到小*/
        for (int i = 0; i < aa.length; i++) {
            Boolean flag;
            int temp = 0;
            for (int j = i + 1; j < aa.length - 1; j++) {
                if (aa[i] < aa[j]) {
                    temp = aa[i];
                    aa[i] = aa[j];
                    aa[j] = temp;

                }

            }

            display(aa);
        }
    }
}
