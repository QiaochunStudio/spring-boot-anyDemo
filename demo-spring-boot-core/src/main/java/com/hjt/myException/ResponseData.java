package com.hjt.myException;

import com.hjt.exception.serviceResponse.ServerResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author :hjt
 * @date : 2022/8/19
 */

public class ResponseData {
    private static final Logger log = LoggerFactory.getLogger(ResponseData.class);

    private String module;
    private String code;
    private String defaultMessage;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public ResponseData(String module, String code, String defaultMessage){
        this.module = module;
        this.code = code;
        this.defaultMessage = defaultMessage;
        log.error("------系统出错-----{}",module+":"+code+":"+defaultMessage);
    }

    public ResponseData(String code, String defaultMessage){
        this.code = code;
        this.defaultMessage = defaultMessage;
        log.error("------系统出错-----{}",code+":"+defaultMessage);
    }



}
