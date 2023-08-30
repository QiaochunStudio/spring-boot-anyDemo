package com.hjt.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SequentialDataProcessing {
    public static void main(String[] args) {
        int totalDataCount = 1000;
        int batchSize = 10;

        // 创建一个固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int startIndex = 0; startIndex < totalDataCount; startIndex += batchSize) {
            final int finalStartIndex = startIndex;
            executorService.submit(() -> processBatch(finalStartIndex, batchSize));
        }

        // 关闭线程池
        executorService.shutdown();
    }

    private static void processBatch(int startIndex, int batchSize) {
        for (int i = startIndex + 1; i <= startIndex + batchSize; i++) {
            // 模拟处理数据，可以将实际的数据处理逻辑放在这里
            System.out.println("Processing data at index: " + i);
        }
    }
}
