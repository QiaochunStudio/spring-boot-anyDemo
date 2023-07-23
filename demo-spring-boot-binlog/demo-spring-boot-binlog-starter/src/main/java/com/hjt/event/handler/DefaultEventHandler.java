package com.hjt.event.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Component
@Slf4j
public class DefaultEventHandler extends AbstractEventHandler {


    @Override
    public void convertData(EventContext context) {

    }

    @Override
    public Boolean filterData(EventContext context) {
        return false;
    }

    @Override
    public Long getTableId(EventContext context) {
        return null;
    }
}
