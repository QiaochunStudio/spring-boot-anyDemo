package com.hjt.annotation;

import lombok.Data;

import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/12
 */
@Data
public class RowListenerModel {

    private String className;
    private String dsName;
    private List<String> tableNames;
    private String methodName;
    private Class<?> clazz;


}
