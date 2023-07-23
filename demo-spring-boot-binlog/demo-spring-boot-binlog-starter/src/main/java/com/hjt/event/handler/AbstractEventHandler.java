package com.hjt.event.handler;

import com.hjt.config.BinLogConfig;
import com.hjt.config.MetaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Component
public abstract class AbstractEventHandler implements EventHandler{

    private static final Logger logger = LoggerFactory.getLogger(AbstractEventHandler.class);


    @Override
    public void handler(EventContext context) {
        // 数据过滤
        Long tableId = getTableId(context);
        if(Objects.isNull(tableId)){
            return;
        }
        context.setTableId(tableId);
        if(!filterData(context)){
            if(Objects.nonNull(context.getLogWarn()) && context.getLogWarn()){
                logger.warn("drop data :{}",context.getEvent());
            }
            return;
        }
        convertData(context);
        if(!CollectionUtils.isEmpty(context.getCustomEventList())){
            context.getCustomEventList().stream().forEach(customEvent -> customEvent.setArriveTime(context.getArriveTime()));
        }
    }


    public abstract void convertData(EventContext context);

    public Boolean filterData(EventContext context){
        String dsName = context.getDataSourceConfig().getDsName();
        long tableId = context.getTableId();
        String tableName = BinLogConfig.getTableName(dsName,
                tableId);
        if (!StringUtils.hasLength(tableName)) {
            return false;
        }
        MetaConfig metaInfo = BinLogConfig.getMetaInfo(dsName, tableName);
        if(Objects.isNull(metaInfo)){
            return false;
        }
        context.setMetaConfig(metaInfo);
        return true;
    }

    public abstract Long getTableId(EventContext context);




}
