package com.hjt.event.handler;

import com.hjt.config.BinLogConfig;
import com.hjt.config.BinLogConfigProperties;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import org.springframework.stereotype.Component;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Component
public class TableMapEventHandler extends AbstractEventHandler {

    @Override
    public void handler(EventContext context) {
        TableMapEventData tableMapEventData = context.getEvent().getData();
        BinLogConfigProperties.DataSourceConfig dataSourceConfig = context.getDataSourceConfig();
        if(tableMapEventData.getDatabase().equals(tableMapEventData.getDatabase()) &&
                dataSourceConfig.getTableFilters().contains(tableMapEventData.getTable())){

            BinLogConfig.updateMapping(tableMapEventData.getDatabase(),tableMapEventData.getTable(),
                    tableMapEventData.getTableId());
        }
    }

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
