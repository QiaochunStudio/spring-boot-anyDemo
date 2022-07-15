package com.hjt.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description:
 * @Author: hjt  <1022134186@qq.com>
 * @Date: 2021/08/18
 */
@Configuration
@ConfigurationProperties("mp")
public class TablesProperties {

    /*
    * 模式
    * */
    private String mode;

    /*
    * 表名
    * */
    private List<String> tableNames;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(List<String> tableNames) {
        this.tableNames = tableNames;
    }
}


