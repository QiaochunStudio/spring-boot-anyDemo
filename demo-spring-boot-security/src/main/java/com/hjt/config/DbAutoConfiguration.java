package com.hjt.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.hjt.constant.Constants;
import com.hjt.domain.DBDriverProperties;
import com.hjt.domain.DbaTables;
import com.hjt.domain.TablesProperties;
import com.hjt.enums.PaSingleton;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

import static com.hjt.domain.Analysis.getSchemaForAnalysisUrl;


/**
 * @Description: 数据库动态配置
 * @Author: hjt  <1022134186@qq.com>
 * @Date: 2021/8/18
 */
@Configuration
public class DbAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TablesProperties tablesProperties() {
        return new TablesProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public DBDriverProperties dbDriverProperties() {
        return new DBDriverProperties();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor(DBDriverProperties dbDriverProperties, TablesProperties tablesProperties) {
        if (selection(dbDriverProperties) != null) {
            PaginationInterceptor paginationInterceptor = PaSingleton.INSTANCE.getInstance();
            setDbTable(tablesProperties, paginationInterceptor);
            PaSingleton.INSTANCE.setDbType(false);
            return paginationInterceptor;
        }
        return null;
    }

    public void setDbTable(TablesProperties tablesProperties, PaginationInterceptor paginationInterceptor) {
        if (null != tablesProperties.getMode() && null != tablesProperties.getTableNames()) {
            List<ISqlParser> sqlParserList = paginationInterceptor.getSqlParserList();
            for (int i = 0; i < sqlParserList.size(); i++) {
                DynamicTableNameParser iSqlParser = (DynamicTableNameParser) sqlParserList.get(i);
                Map<String, ITableNameHandler> tableNameHandlerMap = iSqlParser.getTableNameHandlerMap();
                List<String> tableNames = tablesProperties.getTableNames();
                tableNames.forEach(tableTitle -> tableNameHandlerMap.put(tableTitle, (metaObject, sql, tableName) -> tablesProperties.getMode() + "." + tableName));
            }
        }
    }

    public void setDbTable(List<DbaTables> tableNames, String schema, PaginationInterceptor paginationInterceptor) {
        List<ISqlParser> sqlParserList = paginationInterceptor.getSqlParserList();
        for (int i = 0; i < sqlParserList.size(); i++) {
            DynamicTableNameParser iSqlParser = (DynamicTableNameParser) sqlParserList.get(i);
            Map<String, ITableNameHandler> tableNameHandlerMap = iSqlParser.getTableNameHandlerMap();
            tableNames.forEach(tableTitle -> tableNameHandlerMap.put(tableTitle.getTableName().toLowerCase(), (metaObject, sql, tableName) -> schema + "." + tableName));
        }
    }

    @Bean
    public String selection(DBDriverProperties dbDriverProperties) {
        String driverClassName = dbDriverProperties.getDriverClassName();
        String url = dbDriverProperties.getUrl();
        if (driverClassName == null) {
            DBDriverProperties.Dynamic dynamic = dbDriverProperties.getDynamic();
            if (dynamic != null) {
                DBDriverProperties.Datasource dynamicDatasource = dynamic.getDatasource();
                if (dynamicDatasource != null) {
                    driverClassName = dynamicDatasource.getMaster().getDriverClassName();
                    url = dynamicDatasource.getMaster().getUrl();
                } else {
                    driverClassName = dynamicDatasource.getSlave().getDriverClassName();
                    url = dynamicDatasource.getMaster().getUrl();
                }
            }
        }
        if (driverClassName == null) {
            throw new RuntimeException("数据库驱动属性为空");
        }
        if (Constants.dm8Driver.equals(driverClassName)) {
            return getSchemaForAnalysisUrl(url);
        } else if (Constants.mysqlDriver.equals(driverClassName)) {
            return null;
        } else {
            throw new RuntimeException("数据库驱动不支持");
        }
    }
}

