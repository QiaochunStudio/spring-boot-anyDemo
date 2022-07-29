package com.hjt.api;

/**
 * 封装API的错误码
 * Created by hjt on 2019/4/19.
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
