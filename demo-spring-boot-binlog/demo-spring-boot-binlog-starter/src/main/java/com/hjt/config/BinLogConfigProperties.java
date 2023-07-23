package com.hjt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "binlog.datasource")
public class BinLogConfigProperties {

    private Boolean open  = new Boolean(true);

    private List<DataSourceConfig> dataSourceConfigs = Arrays.asList();

    private Boolean warnLog;

    private String classPath;

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getWarnLog() {
        return warnLog;
    }

    public void setWarnLog(Boolean warnLog) {
        this.warnLog = warnLog;
    }

    public List<DataSourceConfig> getDataSourceConfigs() {
        return dataSourceConfigs;
    }

    public void setDataSourceConfigs(List<DataSourceConfig> dataSourceConfigs) {
        this.dataSourceConfigs = dataSourceConfigs;
    }

    public static class DataSourceConfig {

        private String host;
        private String url;
        private String userName;
        private String passWord;
        private String dsName;
        private Integer port;
        private Long serverId;
        private List<String> tableFilters;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDsName() {
            return dsName;
        }

        public void setDsName(String dsName) {
            this.dsName = dsName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassWord() {
            return passWord;
        }

        public void setPassWord(String passWord) {
            this.passWord = passWord;
        }

        public List<String> getTableFilters() {
            return tableFilters;
        }

        public void setTableFilters(List<String> tableFilters) {
            this.tableFilters = tableFilters;
        }

        public Long getServerId() {
            return serverId;
        }

        public void setServerId(Long serverId) {
            this.serverId = serverId;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }
    }

}