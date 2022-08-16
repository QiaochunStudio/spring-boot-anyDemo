package com.hjt.juc.reentrantReadwriteLock;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 自定义，线程安全的 map ,用读写锁改造HashMap
 */
public class SafeMap {
    //非线程安全的map
    private static Map<String, Object> map = new HashMap<>();
    //可重入读写锁
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    //读锁
    private static Lock r = lock.readLock();
    //写锁
    private static Lock w = lock.writeLock();

    //获取一个key 对应的值
    public static final Object get(String key) {
        //加 读锁
        r.lock();
        Object obj = null;
        try {
            obj = map.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            r.unlock();
        }
        return obj;
    }

    //设置 key 对应的value
    public static final Object put(String key, Object value) {
        //加 写锁
        w.lock();
        Object obj = null;
        try {
            obj = map.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            w.unlock();
        }
        return obj;
    }
}
