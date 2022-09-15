package com.hjt.service;

import com.alipay.api.AlipayApiException;
import com.hjt.pojo.Order;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:55
 * @Description: 支付 service
 */
public interface AliPayService {
    /**
     * 支付宝支付接口
     * @param order
     * @return
     * @throws AlipayApiException
     */
    String aliPay(Order order) throws AlipayApiException;
}
