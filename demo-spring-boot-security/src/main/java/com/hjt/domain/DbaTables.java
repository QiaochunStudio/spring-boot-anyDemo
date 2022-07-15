package com.hjt.domain;

import com.baomidou.mybatisplus.annotation.TableName;

/*
*
 * @Description: dba_tables
 * @Author: hjt  <1022134186@qq.com>
 * @Date: 2021/08/19
*/
@TableName("dba_tables")
public class DbaTables {

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
