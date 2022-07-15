package com.hjt.domain;

/**
 * @Description: 解析类
 * @Author: hjt  <1022134186@qq.com>
 * @Date: 2021/09/11
 */
public class Analysis {

    /*
    * 解析datasource 地址 获取数据库名称
    * */
    public static String getSchemaForAnalysisUrl(String url) {
        String substring = url.substring(0, url.indexOf('?'));
        return substring.substring(substring.lastIndexOf("/") + 1);
    }
}
