package com.hjt.service.impl;

import com.hjt.domain.Input;
import com.hjt.domain.Output;
import com.hjt.service.Input2OutputService;
import com.hjt.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * 业务实现类
 */
@Service
@Slf4j
public class Input2OutputServiceImpl implements Input2OutputService {
    /**
     * 单个处理
     * @param input 输入对象
     * @return 输出对象
     */
    @Override
    public Output singleProcess(Input input) {
        log.info("Processing...");
        try {

//            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
            return new Output(false, null);
        }
        return new Output(true, String.valueOf(2 * input.getI() + 1));
    }

    /**
     * 批量处理
     * @param inputList 输入对象列表
     * @return 输出对象列表
     */
    @Override
    public List<Output> multiProcess(List<Input> inputList) {
        ThreadPoolTaskExecutor executor
                = SpringUtils.getBean("threadPoolTaskExecutor", ThreadPoolTaskExecutor.class);
        CountDownLatch latch = new CountDownLatch(inputList.size());
        List<Output> outputList = Collections.synchronizedList(new ArrayList<>(inputList.size()));

        for (Input input : inputList) {
            executor.execute(() -> {
                try {

                    Output output = singleProcess(input);
                    outputList.add(output);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return outputList;
    }

    /**
     * 异步处理
     * @param input 输入对象
     * @return 输出Future对象
     */
    @Async("threadPoolTaskExecutor")
    @Override
    public Future<Output> asyncProcess(Input input) {
        return new AsyncResult<>(singleProcess(input));
    }
}
