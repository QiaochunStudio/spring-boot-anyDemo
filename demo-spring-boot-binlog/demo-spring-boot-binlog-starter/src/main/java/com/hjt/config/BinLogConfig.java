package com.hjt.config;

import com.hjt.annotation.RowListenerModel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/11
 */
public class BinLogConfig {

    // 元数据信息
    private static List<MetaConfig> metaConfigList;

    // database-tableName-tableId 映射
    private static List<DataBaseTableIdModel> dataBaseTableIdModels = new ArrayList<>();

    private static Boolean warnLog;

    private static List<RowListenerModel> rowListenerModels = new ArrayList<>();


    public static List<RowListenerModel> getRowListenerModels() {
        return rowListenerModels;
    }

    public static void setRowListenerModels(List<RowListenerModel> rowListenerModels) {
        BinLogConfig.rowListenerModels = rowListenerModels;
    }

    public static List<DataBaseTableIdModel> getDataBaseTableIdModels() {
        return dataBaseTableIdModels;
    }

    public static void setDataBaseTableIdModels(List<DataBaseTableIdModel> dataBaseTableIdModels) {
        BinLogConfig.dataBaseTableIdModels = dataBaseTableIdModels;
    }

    public static Boolean getWarnLog() {
        return warnLog;
    }

    public static void setWarnLog(Boolean warnLog) {
        BinLogConfig.warnLog = warnLog;
    }

    public static List<MetaConfig> getMetaConfigList() {
        return metaConfigList;
    }

    public static void setMetaConfigList(List<MetaConfig> metaConfigList) {
        BinLogConfig.metaConfigList = metaConfigList;
    }

    public static void addMetaConfigList(List<MetaConfig> metaConfigList) {
        if(null == BinLogConfig.metaConfigList){
            BinLogConfig.metaConfigList = new ArrayList<>();
        }
        BinLogConfig.metaConfigList.addAll(metaConfigList);
    }

    public static void updateMapping(String dsName,String tableName,Long tableId){
        DataBaseTableIdModel dataBaseTableIdModelMap = dataBaseTableIdModels.stream().filter(dataBaseTableIdModel ->
                dataBaseTableIdModel.getDsName().equals(dsName) && dataBaseTableIdModel.getTableName().equals(tableName))
                .findFirst().orElse(null);
        if(Objects.nonNull(dataBaseTableIdModelMap)){
            if(!dataBaseTableIdModelMap.getTableId().equals(tableId)){
                dataBaseTableIdModelMap.setTableId(tableId);
            }
        }else {
            DataBaseTableIdModel dataBaseTableIdModel = new DataBaseTableIdModel();
            dataBaseTableIdModel.setTableId(tableId);
            dataBaseTableIdModel.setTableName(tableName);
            dataBaseTableIdModel.setDsName(dsName);
            dataBaseTableIdModels.add(dataBaseTableIdModel);
        }
    }

    public static String getTableName(String dsName,Long tableId){
        DataBaseTableIdModel filterResult = dataBaseTableIdModels.stream().filter(dataBaseTableIdModel -> dataBaseTableIdModel.getDsName().equals(dsName)
                && dataBaseTableIdModel.getTableId().equals(tableId)).findFirst().orElse(null);
        if(Objects.isNull(filterResult)){
            return "";
        }
        return filterResult.getTableName();
    }

    public static MetaConfig getMetaInfo(String dsName,String tableName){
        if(CollectionUtils.isEmpty(metaConfigList)){
            return null;
        }
        MetaConfig filterResult = metaConfigList.stream().filter(metaConfig -> metaConfig.getDsName().equals(dsName) && metaConfig.getTableName().equals(tableName)).findFirst()
                .orElse(null);
        if(Objects.isNull(filterResult)){
            return null;
        }
        return filterResult;
    }

}
