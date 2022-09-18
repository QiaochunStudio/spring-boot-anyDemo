package com.hjt.config;

/**
 * @author hjt
 * @date:2022/9/18
 */
import lombok.Data;

@Data
public class AliPayDto {
    private String traceNo;
    private String totalAmount;
    private String subject;
    private String alipayTraceNo;
}
