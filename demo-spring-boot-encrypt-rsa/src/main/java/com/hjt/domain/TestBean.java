package com.hjt.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class TestBean implements Serializable {
    private String name;
    private Integer age;
    private long timestamp;
}
