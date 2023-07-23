package com.hjt.queue;

import com.alibaba.fastjson.JSON;
import com.hjt.annotation.RowListenerModel;
import com.hjt.autoconfigure.cache.BeanCache;
import com.hjt.event.CustomEvent;
import com.google.common.cache.Cache;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/13
 */
@Data
public class ConsumerTask implements Runnable{


    private ApplicationContext applicationContext;
    private CustomQueue customQueue;
    private Cache<Class<?>, BeanCache> localCache;

    public ConsumerTask(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public ConsumerTask(ApplicationContext applicationContext, CustomQueue customQueue) {
        this.applicationContext = applicationContext;
        this.customQueue = customQueue;
    }

    public ConsumerTask(ApplicationContext applicationContext, CustomQueue customQueue, Cache<Class<?>, BeanCache> localCache) {
        this.applicationContext = applicationContext;
        this.customQueue = customQueue;
        this.localCache = localCache;
    }

    @Override
    public void run() {
        while (true){
            if(customQueue.getSize() > 0){
                QueueModel queueModel = customQueue.poll();
                if(Objects.nonNull(queueModel)){
                    invokeMethod(queueModel.getRowListenerModel(),queueModel.getCustomEvent());
                }
            }
        }
    }

    private void invokeMethod(RowListenerModel rowListenerModel, CustomEvent customEvent){
        BeanCache beanCache = localCache.getIfPresent(rowListenerModel.getClazz());
        if(Objects.isNull(beanCache)){
            beanCache = new BeanCache();
            Object bean = applicationContext.getBean(rowListenerModel.getClazz());
            Class<? extends Object> [] paramClass = new Class[1];
            paramClass[0] = String.class;
            Method method = ReflectionUtils.findMethod(bean.getClass(), rowListenerModel.getMethodName(),paramClass);
            beanCache.addMethod(method);
            beanCache.setObject(bean);
            localCache.put(rowListenerModel.getClazz(),beanCache);
            ReflectionUtils.invokeMethod(method,bean, JSON.toJSONString(customEvent));
        }else {
            if(Objects.isNull(beanCache.getObject())){
                Object bean = applicationContext.getBean(rowListenerModel.getClazz());
                beanCache.setObject(bean);
            }
            if(Objects.isNull(beanCache.getMethod(rowListenerModel.getMethodName()))){
                Class<? extends Object> [] paramClass = new Class[1];
                paramClass[0] = String.class;
                Method method = ReflectionUtils.findMethod(beanCache.getObject().getClass(), rowListenerModel.getMethodName(),paramClass);
                beanCache.addMethod(method);
            }
            Method method = beanCache.getMethod(rowListenerModel.getMethodName());
            ReflectionUtils.invokeMethod(method,beanCache.getObject(),JSON.toJSONString(customEvent));
        }
    }
    
}
