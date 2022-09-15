package com.hjt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:49
 * @Description: 商品订单实体类(支付实体对象)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Order {
    /**
     * 商户订单号，必填
     */
    private String out_trade_no;
    /**
     * 订单名称，必填
     */
    private String subject;
    /**
     * 付款金额，必填
     * 根据支付宝接口协议，必须使用下划线
     */
    private String total_amount;
    /**
     * 商品描述，可空
     */
    private String description;
    /**
     * 超时时间参数
     */
    private String timeout_express = "10m";
    /**
     * 产品编号
     */
    private String product_code = "FAST_INSTANT_TRADE_PAY";
}
