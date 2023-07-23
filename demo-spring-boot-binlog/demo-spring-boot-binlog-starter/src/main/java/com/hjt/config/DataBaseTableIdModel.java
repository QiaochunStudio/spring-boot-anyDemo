package com.hjt.config;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
public class DataBaseTableIdModel {

    private String dsName;
    private String tableName;
    private Long tableId;

    public String getDsName() {
        return dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
