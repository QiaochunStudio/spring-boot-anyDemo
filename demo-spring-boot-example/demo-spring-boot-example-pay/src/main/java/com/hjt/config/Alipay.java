package com.hjt.config;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.hjt.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:57
 * @Description: 调用支付宝支付的组件
 */
@Component
public class Alipay {

    @Autowired
    private AliPayConfig alipayConfig;

    /**
     * 支付接口
     *
     * @param order
     * @return
     * @throws AlipayApiException
     */
    public String pay(Order order) throws AlipayApiException {

        // 支付宝网关
        String serverUrl = alipayConfig.getGatewayUrl();
        // APPID
        String appId = alipayConfig.getAppId();
        // 商户私钥, 即PKCS8格式RSA2私钥
        String privateKey = alipayConfig.getPrivateKey();
        // 格式化为 json 格式
        String format = "json";
        // 字符编码格式
        String charset = alipayConfig.getCharset();
        // 支付宝公钥, 即对应APPID下的支付宝公钥
        String alipayPublicKey = alipayConfig.getPublicKey();
        // 签名方式
        String signType = alipayConfig.getSignType();
        // 页面跳转同步通知页面路径
        String returnUrl = alipayConfig.getReturnUrl();
        // 服务器异步通知页面路径
        String notifyUrl = alipayConfig.getNotifyUrl();

        // 1、获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);

        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数(以json格式封装)
        alipayRequest.setBizContent(JSON.toJSONString(order));

        // 3、请求支付宝进行付款，并获取支付结果

        AlipayTradePagePayResponse response =  alipayClient.pageExecute(alipayRequest);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }
}
