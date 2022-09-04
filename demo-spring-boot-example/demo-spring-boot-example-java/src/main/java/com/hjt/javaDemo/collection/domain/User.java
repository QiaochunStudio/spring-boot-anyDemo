package com.hjt.javaDemo.collection.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
        private Integer id;
        private String userName;
        private Integer age;
}
