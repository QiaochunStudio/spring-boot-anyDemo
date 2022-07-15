package com.hjt.enums;

import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

import java.util.Collections;
import java.util.HashMap;

public enum PaSingleton {

    INSTANCE;

    private PaginationInterceptor instance;

    /*是否为mysql数据库*/
    private Boolean dbType = true;

    PaSingleton() {
        instance = new PaginationInterceptor();
        DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser().setTableNameHandlerMap(new HashMap<String, ITableNameHandler>(2));
        instance.setSqlParserList(Collections.singletonList(dynamicTableNameParser));
    }

    public PaginationInterceptor getInstance() {
        return instance;
    }

    public void setDbType(boolean bo){
        this.dbType = bo;
    }

    public Boolean getDbType(){
        return this.dbType;
    }
}
