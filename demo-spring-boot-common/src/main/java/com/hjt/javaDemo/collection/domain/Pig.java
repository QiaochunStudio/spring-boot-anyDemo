package com.hjt.javaDemo.collection.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pig implements Serializable {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private boolean valid;
}
