package com.hjt.advice;

import com.hjt.annotation.Encrypt;
import com.hjt.config.RSAUtilsConfig;
import com.hjt.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * Author:Bobby
 * DateTime:2019/4/9
 * 出参进行加密操作
 **/
@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean encrypt;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RSAUtils rsaUtils;


    @Autowired
    private RSAUtilsConfig rsaUtilsConfig;

    private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = returnType.getMethod();
        if (Objects.isNull(method)) {
            return encrypt;
        }
        encrypt = method.isAnnotationPresent(Encrypt.class) && rsaUtilsConfig.isOpen();
        return encrypt;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // EncryptResponseBodyAdvice.setEncryptStatus(false);
        // Dynamic Settings Not Encrypted
        Boolean status = encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
            return body;
        }
        if (encrypt) {
            String publicKey = rsaUtilsConfig.getPublicKey();
            try {
                String content = JsonUtils.writeValueAsString(body);
                if (!StringUtils.hasText(publicKey)) {
                    throw new NullPointerException("Please configure rsa.encrypt.privatekeyc parameter!");
                }
                String privateKeyMap = "";
                String publicKeyMap = "";
                Long userId = SecurityUtils.getUserId();
                /*判断用户是否存了RSA公私钥*/
                if(redisUtil.hasKey("rsa:privateKey:" + userId)){
                    privateKeyMap = (String)redisUtil.get("rsa:privateKey:" + userId);
                }
                /*初始化*/
                else{
                    /*初始化秘钥 并且根据用户获取私钥*/
                    Map<String, Object> keyMap = rsaUtils.genKeyPair();
                    privateKeyMap = rsaUtils.getPrivateKey(keyMap);
                    publicKeyMap = rsaUtils.getPublicKey(keyMap);
                    /*存放redis*/
                    redisUtil.set("rsa:privateKey:" + userId,privateKeyMap);
                    redisUtil.set("rsa:publicKey:" + userId,publicKeyMap);
                }
                /*私钥加密*/
                byte[] encryptByPrivateKey = RSAUtils.encryptByPrivateKey(content.getBytes(), privateKeyMap);
                String result = Base64Util.encode(encryptByPrivateKey);
                if(rsaUtilsConfig.isShowLog()) {
                    log.info("Pre-encrypted data：{}，After encryption：{}", content, result);
                }
                return result;
            } catch (Exception e) {
                log.error("Encrypted data exception", e);
            }
        }

        return body;
    }
}
