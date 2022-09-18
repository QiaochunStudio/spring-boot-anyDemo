package com.hjt.config;

/**
 * @author hjt
 * @date:2022/9/18
 */
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Data
@Component
public class AliPayDtoConfig {
    @Resource
    private AliPayConfig aliPayConfig;


    @PostConstruct
    public void init() {
        // 设置参数（全局只需设置一次）
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipaydev.com";
        config.signType = "RSA2";
        config.appId = aliPayConfig.getAppId();
        config.merchantPrivateKey = aliPayConfig.getPrivateKey();
        config.alipayPublicKey = aliPayConfig.getPublicKey();
        config.notifyUrl = aliPayConfig.getNotifyUrl();
        Factory.setOptions(config);
        System.out.println("=======支付宝SDK初始化成功=======");
    }

}

