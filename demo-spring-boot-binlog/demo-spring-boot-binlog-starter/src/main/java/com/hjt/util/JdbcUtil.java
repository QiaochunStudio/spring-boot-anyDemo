package com.hjt.util;

import com.hjt.config.BinLogConfigProperties;
import com.hjt.config.ColumnConfig;
import com.hjt.config.MetaConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/11
 */
@Slf4j
public class JdbcUtil {

    private static final String columnNameLabel = "COLUMN_NAME";
    private static final String columnPosition = "ORDINAL_POSITION";

    public static List<MetaConfig> getMetaConfigList(BinLogConfigProperties.DataSourceConfig dataSourceConfig){
        Connection connection = null;
        DatabaseMetaData metaData = null;
        List<MetaConfig> result = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUserName(), dataSourceConfig.getPassWord());
            metaData = connection.getMetaData();
            for (String tableName : dataSourceConfig.getTableFilters()) {
                MetaConfig metaConfig = new MetaConfig();
                List<ColumnConfig> columnConfig = getColumnConfig(metaData, tableName, dataSourceConfig.getDsName());
                metaConfig.setDsName(dataSourceConfig.getDsName());
                metaConfig.setTableName(tableName);
                metaConfig.setColumnConfigs(columnConfig);
                result.add(metaConfig);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public static List<ColumnConfig> getColumnConfig(DatabaseMetaData metaData,String tableName,String catalog){
        if(Objects.isNull(metaData)){
            return new ArrayList<>();
        }
        List<ColumnConfig> columnConfigList = new ArrayList<>();
        ResultSet columnSet = null;
        try {
            columnSet = metaData.getColumns(catalog, null, tableName, "%");
            while (columnSet.next()){
                String columnName = columnSet.getString(columnNameLabel);
                int index = columnSet.getInt(columnPosition);
                ColumnConfig config = new ColumnConfig();
                config.setColumn(columnName);
                config.setIndex(--index);
                columnConfigList.add(config);
            }
        }catch (Exception e){
            log.error("fail to get columnMetadata,e:{}",e);
        } finally {
            closeResultSet(columnSet);
        }
        return columnConfigList;
    }


    public static void closeResultSet(ResultSet resultSet){
        if(Objects.nonNull(resultSet)){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                log.error("fail to close resultSet,e:{}",throwables);
            }
        }
    }

    public static void closeConnection(Connection connection){
        if(Objects.nonNull(connection)){
            try {
                connection.close();
            } catch (SQLException throwables) {
                log.error("fail to close connection,e:{}",throwables);
            }
        }
    }

    public static void fullParam(BinLogConfigProperties.DataSourceConfig dataSourceConfig) {
        String url = dataSourceConfig.getUrl();
        Pattern p = Pattern.compile("jdbc:(?<db>\\w+):.*((//)|@)(?<host>.+):(?<port>\\d+)(/|(;DatabaseName=)|:)(?<dbName>\\w+)\\??.*");
        Matcher m = p.matcher(url);
        if(m.find()) {
            if(Objects.isNull(dataSourceConfig.getHost())){
                dataSourceConfig.setHost(m.group("host"));
            }
            if(Objects.isNull(dataSourceConfig.getDsName())){
                dataSourceConfig.setDsName(m.group("dbName"));
            }
            if(Objects.isNull(dataSourceConfig.getPort())){
                dataSourceConfig.setPort(Integer.valueOf(m.group("port")));
            }
        }
    }


}
