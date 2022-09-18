package com.hjt.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.easysdk.factory.Factory;
import com.hjt.config.AliPayConfig;
import com.hjt.config.AliPayDto;
import com.hjt.pojo.Order;
import com.hjt.service.AliPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.alipay.api.AlipayConstants.FORMAT;
import static org.apache.catalina.manager.Constants.CHARSET;

import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URLEncoder;

/**
 * @Auther: csp1999
 * @Date: 2020/11/13/21:47
 * @Description: 支付宝沙箱测试 controller
 */
@RestController
public class PayController {
    private static final Logger log = LoggerFactory.getLogger(PayController.class);


    @Autowired
    private AliPayService aliPayService;

    @Resource
    private AliPayConfig aliPayConfig;

    /**
     * 支付宝支付 api
     *
     * @param outTradeNo
     * @param subject
     * @param totalAmount
     * @param description
     * @return
     * @throws AlipayApiException
     */
    @GetMapping(value = "/order/alipay")
    public void alipay(String outTradeNo, String subject,
                         String totalAmount, String description, HttpServletResponse httpResponse) throws AlipayApiException, IOException {
        Order order = new Order();
        order.setOut_trade_no(outTradeNo);
        order.setSubject(subject);
        order.setTotal_amount(totalAmount);
        order.setDescription(description);
        System.out.println(order);

        //解决中文乱码问题
        String form = "";
        form = aliPayService.aliPay(order);
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

        log.info("----form----{}",form);
    }

    @PostMapping(value = "/order/alipay/isPayOver")
    public String isPayOver(String outTradeNo) throws AlipayApiException {
        return aliPayService.isPayOver(outTradeNo);
    }

    @PostMapping(value = "/order/alipay/receivePayInfo")
    public String receivePayInfo(String trade_no, String out_trade_no) throws AlipayApiException {
        return aliPayService.receivePayInfo(trade_no, out_trade_no);
    }

    @PostMapping(value = "/order/alipay/testReceivePayInfo")
    public String testReceivePayInfo(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        log.info("-----------开始进行支付后的异步通知回调-------");
        /*支付宝官方例子接收参数*/
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用 这个不能加 不然异步通知回调的时候验签可能会失败
//            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        /*支付宝官方例子接收参数*/

        log.info("---------接收的参数--- -----{}",params);
        //验证签名（支付宝公钥）
        boolean  signVerified = AlipaySignature.rsaCheckV1(params, aliPayConfig.getPublicKey(), aliPayConfig.getCharset(), aliPayConfig.getSignType());  //调用SDK验证签名
        if (signVerified){
            // 收到支付宝异步通知,返回success,支付宝不再通知 否则会通知你三天三夜
//            log.info("收到支付宝通知: {}",payAsyncVo);
            log.info("-----验签成功-----");
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商家自身业务处理，校验失败返回failure
        } else {
            log.info("-----验签失败-----");
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }

        return "测试是否成功进来";
    }

    @GetMapping("/pay") // &subject=xxx&traceNo=xxx&totalAmount=xxx
    public void pay(AliPayDto aliPay, HttpServletResponse httpResponse) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(), aliPayConfig.getAppId(),
                aliPayConfig.getPrivateKey(), aliPayConfig.getFormat(), CHARSET, aliPayConfig.getPublicKey(), aliPayConfig.getSignType());
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setBizContent("{\"out_trade_no\":\"" + aliPay.getTraceNo() + "\","
                + "\"total_amount\":\"" + aliPay.getTotalAmount() + "\","
                + "\"subject\":\"" + aliPay.getSubject() + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

}
