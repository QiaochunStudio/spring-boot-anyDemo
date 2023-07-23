package com.hjt.config;

import lombok.Data;

import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/11
 */
@Data
public class MetaConfig {

    private List<ColumnConfig> columnConfigs;
    private String dsName;
    private String tableName;

}
