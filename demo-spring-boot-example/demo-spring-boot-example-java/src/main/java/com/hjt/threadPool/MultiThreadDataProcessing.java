package com.hjt.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadDataProcessing {
    public static void main(String[] args) {
        int totalData = 1000;
        int batchSize = 10;
        int numThreads = totalData / batchSize;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        List<Integer> data = new ArrayList<>();
        for (int i = 1; i <= totalData; i++) {
            data.add(i);

            if (data.size() == batchSize) {
                List<Integer> batchData = new ArrayList<>(data);
                executorService.execute(() -> processBatchData(batchData));
                data.clear();
            }
        }

        executorService.shutdown();
    }

    private static void processBatchData(List<Integer> batchData) {
        for (Integer data : batchData) {
            // Process data here
            System.out.println("Processing data: " + data);
        }
    }
}

