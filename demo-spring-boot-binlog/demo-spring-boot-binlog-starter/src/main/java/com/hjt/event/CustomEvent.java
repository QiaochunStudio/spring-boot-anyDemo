package com.hjt.event;

import lombok.Data;

import java.util.List;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/11
 */
@Data
public class CustomEvent {

    private String dsName;
    private String tableName;
    private Integer operation;
    private List<FieldValue> fieldValueList;
    // 到达服务器时间
    private Long arriveTime;

}



