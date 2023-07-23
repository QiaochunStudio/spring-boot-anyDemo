package com.hjt.event;

import lombok.Data;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/7/11
 */
@Data
public class FieldValue {

    private String fieldName;
    private Object beforeValue;
    private Object afterValue;
    private String fieldType;
}
