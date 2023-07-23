package com.hjt.autoconfigure;

import com.alibaba.fastjson.JSON;
import com.hjt.annotation.AnnotationUtil;
import com.hjt.annotation.RowListener;
import com.hjt.annotation.RowListenerModel;
import com.hjt.autoconfigure.cache.BeanCache;
import com.hjt.config.BinLogConfig;
import com.hjt.config.BinLogConfigProperties;
import com.hjt.config.MetaConfig;
import com.hjt.event.CustomEvent;
import com.hjt.event.handler.EventContext;
import com.hjt.event.handler.EventHandler;
import com.hjt.event.handler.EventHandlerSelector;
import com.hjt.queue.ConsumerTask;
import com.hjt.queue.CustomQueue;
import com.hjt.queue.QueueModel;
import com.hjt.util.JdbcUtil;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/7
 */
@Configuration
@EnableConfigurationProperties(BinLogConfigProperties.class)
@Slf4j
public class BinLogAutoConfiguration implements BeanPostProcessor, Ordered, ApplicationContextAware, InitializingBean, SmartInitializingSingleton {


    private ApplicationContext applicationContext;
    private final BinLogConfigProperties binLogConfigProperties;
    private final EventHandlerSelector eventHandlerSelector;
    private final AnnotationUtil annotationUtil;
    private Cache<Class<?>, BeanCache> localCache = CacheBuilder.newBuilder()
                     // 并发线程
                    .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                     // 初始容量
                    .initialCapacity(100)
                     // 最大容量
                    .maximumSize(1000)
                    .build();
    private CustomQueue customQueue = new CustomQueue();
    ExecutorService executorService;
    private Integer retry = 3;

    public BinLogAutoConfiguration(BinLogConfigProperties binLogConfigProperties, EventHandlerSelector eventHandlerSelector, AnnotationUtil annotationUtil) {
        this.binLogConfigProperties = binLogConfigProperties;
        this.eventHandlerSelector = eventHandlerSelector;
        this.annotationUtil = annotationUtil;
    }

    @Override
    public void afterPropertiesSet() {
        if (!binLogConfigProperties.getOpen()) {
            return;
        }
        List<BinLogConfigProperties.DataSourceConfig> dataSourceConfigs = binLogConfigProperties.getDataSourceConfigs();
        if(CollectionUtils.isEmpty(dataSourceConfigs)){
            return;
        }
        getRowListener();
        executorService =  Executors.newFixedThreadPool(dataSourceConfigs.size());
        // 初始化数据源拿到所有元数据信息配置信息
        if (!CollectionUtils.isEmpty(dataSourceConfigs)) {
            for (BinLogConfigProperties.DataSourceConfig dataSourceConfig : dataSourceConfigs) {
                // 参数信息补充
                JdbcUtil.fullParam(dataSourceConfig);
                //获取元数据信息
                getMetaInfo(dataSourceConfig);
            }
        }
        // 日志打印配置
        BinLogConfig.setWarnLog(binLogConfigProperties.getWarnLog());
        // 拿到所有配置的数据源
        if (!CollectionUtils.isEmpty(dataSourceConfigs)) {
            List<CompletableFuture<Void>> taskList = new ArrayList<>();
            for (BinLogConfigProperties.DataSourceConfig dataSourceConfig : dataSourceConfigs) {
                new Thread(() -> {
                    dataSourceRegister(dataSourceConfig,customQueue);
                }).start();
            }
            for (int i = 0; i < dataSourceConfigs.size(); i++) {
                executorService.execute(new ConsumerTask(applicationContext,customQueue,localCache));
            }
        }
    }

    private void dataSourceRegister(BinLogConfigProperties.DataSourceConfig dataSourceConfig,CustomQueue customQueue){
        BinaryLogClient client = new BinaryLogClient(dataSourceConfig.getHost(), dataSourceConfig.getPort(), dataSourceConfig.getUserName(), dataSourceConfig.getPassWord());
        client.setServerId(dataSourceConfig.getServerId());
        client.registerEventListener(event -> {
            try {
                EventHandler handler = eventHandlerSelector.getHandler(event);
                EventContext context = new EventContext();
                context.setDataSourceConfig(dataSourceConfig);
                context.setEvent(event);
                context.setArriveTime(System.currentTimeMillis());
                context.setLogWarn(binLogConfigProperties.getWarnLog());
                handler.handler(context);
                if (!CollectionUtils.isEmpty(context.getCustomEventList())) {
                    offerData(context.getCustomEventList(),customQueue);
                }
            } catch (Exception e) {
                log.error("fail to handler event,e:{}",e);
            }
        });
        try {
            client.connect();
        } catch (IOException e) {
            log.warn("fail to connect ds,e:{},param:{}", e, JSON.toJSONString(dataSourceConfig));
        }
    }


    @Override
    public void afterSingletonsInstantiated() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }


    private void getMetaInfo(BinLogConfigProperties.DataSourceConfig dataSourceConfig) {
        List<MetaConfig> metaConfigList = JdbcUtil.getMetaConfigList(dataSourceConfig);
        BinLogConfig.addMetaConfigList(metaConfigList);
    }

    private void getRowListener(){
        try {
            annotationUtil.getAllAddTagAnnotation(binLogConfigProperties.getClassPath(),RowListener.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void offerData(List<CustomEvent> customEventList,CustomQueue customQueue){
        List<RowListenerModel> rowListenerModels = BinLogConfig.getRowListenerModels();
        if(CollectionUtils.isEmpty(rowListenerModels)){
            return;
        }
        if(CollectionUtils.isEmpty(customEventList)){
            return;
        }
        for (CustomEvent customEvent : customEventList) {
            for (RowListenerModel rowListenerModel : rowListenerModels) {
                if(rowListenerModel.getDsName().equals(customEvent.getDsName())
                    ){
                    List<String> tableNames = rowListenerModel.getTableNames();
                    if(!CollectionUtils.isEmpty(tableNames)){
                        if(!tableNames.contains(customEvent.getTableName())){
                            continue;
                        }
                    }
                    QueueModel queueModel = new QueueModel();
                    queueModel.setRowListenerModel(rowListenerModel);
                    queueModel.setCustomEvent(customEvent);
                    Boolean offer = customQueue.offer(queueModel);
                    if(!offer){
                        // 重试3次
                        retry(queueModel);
                    }
                }
            }
        }
    }

    private void retry(QueueModel queueModel){
        int num = retry;
        while (num > 0){
            try {
                TimeUnit.MILLISECONDS.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Boolean offer = customQueue.offer(queueModel);
            if(!offer){
                num--;
            }else {
                return;
            }
        }
    }


}
