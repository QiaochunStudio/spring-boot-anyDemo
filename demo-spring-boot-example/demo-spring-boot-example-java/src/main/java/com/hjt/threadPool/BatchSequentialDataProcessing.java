package com.hjt.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BatchSequentialDataProcessing {
    public static void main(String[] args) {
        int totalDataCount = 1000;
        int batchSize = 10;
        int numThreads = 4; // 可根据需要调整线程数

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int startIndex = 0; startIndex < totalDataCount; startIndex += batchSize) {
            final int finalStartIndex = startIndex;
            executorService.submit(() -> processBatch(finalStartIndex, batchSize));
        }

        // 关闭线程池
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void processBatch(int startIndex, int batchSize) {
        for (int i = startIndex + 1; i <= startIndex + batchSize; i++) {
            // 模拟处理数据，可以将实际的数据处理逻辑放在这里
            System.out.println("Processing data at index: " + i);
        }
    }
}

