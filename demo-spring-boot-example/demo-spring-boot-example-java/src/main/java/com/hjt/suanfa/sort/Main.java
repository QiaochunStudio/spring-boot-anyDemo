package com.hjt.suanfa.sort;

/**
 * @author :hjt
 * @date : 2022/11/17
 */
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        int[] seedArrInt = {-2129471197,-2134112042,-2147349214,-1834553516,-2147480540,-2070354878,-2145306098,-2147075913};
        for (int seed : seedArrInt ){
            System.out.print(fuckNum(seed));
        }
    }
    public static String fuckNum(long i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(78);
            if (k == 0) {
                break;
            }
            sb.append((char) ('-' + k));
        }
        return sb.toString();
    }
}