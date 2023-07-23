package com.hjt.config;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2023/5/29
 */
public enum OperateTypeEnum {

    INSERT(1,"新增"),
    UPDATE(2,"更新"),
    DELETE(3,"删除");


    private Integer code;
    private String desc;

    OperateTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
