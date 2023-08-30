package com.hjt.hutool.http;

import lombok.Data;

import java.util.List;

@Data
public class HttpResponseDataVo {
    private String msg;
    private String code;
    private List<TMessageNotice> data;
}
