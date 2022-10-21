package com.hjt.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author :hjt
 * @date : 2022/10/20
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(DateUtil.newParse("2022-07-24 16:34:30"));
            });
        }
        executorService.shutdown();

    }
}
