package com.hjt.event.handler;

import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Service
public class EventHandlerSelector {

    private static final Logger logger = LoggerFactory.getLogger(EventHandlerSelector.class);

    @Autowired
    private DeleteRowEventHandler deleteRowEventHandler;
    @Autowired
    private TableMapEventHandler tableMapEventHandler;
    @Autowired
    private WriteRowEventHandler writeRowEventHandler;
    @Autowired
    private UpdateEventHandler updateEventHandler;
    @Autowired
    private DefaultEventHandler defaultEventHandler;

    public EventHandler getHandler(Event event){
        // 考虑到状态映射的问题，只在增删改是更新位置
        if (EventType.isUpdate(event.getHeader().getEventType())) {
            return updateEventHandler;
        } else if (EventType.isWrite(event.getHeader().getEventType())) {
            return writeRowEventHandler;
        } else if (EventType.isDelete(event.getHeader().getEventType())) {
            return deleteRowEventHandler;
        } else if (EventType.TABLE_MAP.equals(event.getHeader().getEventType())) {
            return tableMapEventHandler;
        }  else {
            logger.debug("不处理事件,{}", event);
            return defaultEventHandler;
        }
    }


}
