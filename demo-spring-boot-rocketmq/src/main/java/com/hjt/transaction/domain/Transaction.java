package com.hjt.transaction.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Transaction implements Serializable {
    private String id;
    private String content;
}
