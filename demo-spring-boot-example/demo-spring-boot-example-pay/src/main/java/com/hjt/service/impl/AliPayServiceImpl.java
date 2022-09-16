package com.hjt.service.impl;

import com.alipay.api.AlipayApiException;
import com.hjt.config.Alipay;
import com.hjt.pojo.Order;
import com.hjt.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:56
 * @Description: 支付service 实现类
 */
@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(Order order) throws AlipayApiException {
        return alipay.pay(order);
    }

    @Override
    public String isPayOver(String outTradeNo) throws AlipayApiException {
        return alipay.isPayOver(outTradeNo);
    }

    @Override
    public String receivePayInfo(String trade_no, String out_trade_no) throws AlipayApiException {
        return alipay.receivePayInfo(trade_no,out_trade_no);
    }
}
