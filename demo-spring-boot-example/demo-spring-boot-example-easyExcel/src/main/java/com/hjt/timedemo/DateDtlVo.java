package com.hjt.timedemo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DateDtlVo {

    @ApiModelProperty("左")
    private String bef;
    @ApiModelProperty("右")
    private String rgt;

    @ApiModelProperty("年")
    private String year;
    @ApiModelProperty("月")
    private String month;
    @ApiModelProperty("日")
    private String day;

    @ApiModelProperty("时")
    private String hour;
    @ApiModelProperty("分")
    private String min;
    @ApiModelProperty("秒")
    private String sec;

}