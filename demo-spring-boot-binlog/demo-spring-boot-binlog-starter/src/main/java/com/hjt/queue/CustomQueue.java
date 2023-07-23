package com.hjt.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/13
 */
public class CustomQueue {

    private static final Logger logger = LoggerFactory.getLogger(CustomQueue.class);
    // 阀值
    private static int THRESHOLD = 10000;
    private volatile int CURRENT_DATA_SIZE = 0;
    private final ConcurrentLinkedQueue<QueueModel> queue = new ConcurrentLinkedQueue();

    public Boolean offer(QueueModel queueModel){
        if (CURRENT_DATA_SIZE > THRESHOLD * 90 / 100) {
            logger.warn("THRESHOLD {}, CURRENT_DATA_SIZE {}, 90% space is used, Sleep {}", THRESHOLD, CURRENT_DATA_SIZE, 2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        boolean offer = queue.offer(queueModel);
        if(offer){
            CURRENT_DATA_SIZE++;
        }
        return offer;
    }

    public QueueModel poll(){
        QueueModel queueModel = queue.poll();
        if(Objects.isNull(queueModel)){
            return null;
        }
        CURRENT_DATA_SIZE--;
        return queueModel;
    }

    public Integer getSize(){
        return CURRENT_DATA_SIZE;
    }

}
