package com.hjt.advice;

import com.hjt.annotation.Decrypt;
import com.hjt.config.RSAUtilsConfig;
import com.hjt.exception.EncryptRequestException;
import com.hjt.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Author:Bobby
 * DateTime:2019/4/9
 **/
@Component
public class DecryptHttpInputMessage implements HttpInputMessage {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private HttpHeaders headers;
    private InputStream body;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RSAUtilsConfig rsaUtilsConfig;




    /***
     * 初始化
     * @param inputMessage
     * @throws Exception
     */
    public DecryptHttpInputMessage initDecryptHttpInputMessage(HttpInputMessage inputMessage, RSAUtilsConfig rsaUtilsConfig, Decrypt decrypt) throws Exception {
        DecryptHttpInputMessage decryptHttpInputMessage = new DecryptHttpInputMessage();
        String privateKey = rsaUtilsConfig.getPrivateKey();
        String charset = rsaUtilsConfig.getCharset();
        boolean showLog = rsaUtilsConfig.isShowLog();
        boolean timestampCheck = rsaUtilsConfig.isTimestampCheck();


        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("privateKey is null");
        }

        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        log.info("----要解密的内容："+content);
        String decryptBody  = "";

        boolean hasRSAContent = redisUtil.hasKey("rsa:params:" + content);
        if (hasRSAContent) {
            throw new EncryptRequestException("参数加密内容已失效");
        }
        /*根据用户获取私钥*/
        Long userId = SecurityUtils.getUserId();
        String publicKey = (String)redisUtil.get("rsa:publicKey:" + userId);
        //base64解密
        byte[] decode = Base64Util.decode(content);
        /*公钥解密*/
        byte[] decodeByPublicKey = RSAUtils.decryptByPublicKey(decode,publicKey);
        decryptBody  = new String(decodeByPublicKey);
        //RSA解密
        //未加密内容
//        if (content.startsWith("{")) {
//            // 必须加密
//            if (decrypt.required()) {
//                log.error("not support unencrypted content:{}", content);
//                throw new EncryptRequestException("not support unencrypted content");
//            }
//            log.info("Unencrypted without decryption:{}", content);
//            decryptBody = content;
//        } else {
//            StringBuilder json = new StringBuilder();
//            content = content.replaceAll(" ", "+");
//
//            if (!StringUtils.isEmpty(content)) {
//                String[] contents = content.split("\\|");
//                for (String value : contents) {
//                    /*RSA解密*/
//                    value = new String(RSACoder.decryptByPrivateKey(Base64Util.decode(value),secretKeyConfig.getPrivateKey()));
////                    value = new String(RSAUtil.decrypt(Base64Util.decode(value), privateKey), charset);
//                    json.append(value);
//                }
//            }
//            decryptBody = json.toString();
//            if (showLog) {
//                log.info("Encrypted data received：{},After decryption：{}", content, decryptBody);
//            }
//        }

//        // 开启时间戳检查
        if (timestampCheck) {
            // 容忍最小请求时间
            long toleranceTime = System.currentTimeMillis() - decrypt.timeout();
            long requestTime = JsonUtils.getNode(decryptBody, "timestamp").asLong();
            // 如果请求时间小于最小容忍请求时间, 判定为超时
            if (requestTime < toleranceTime) {
                log.error("Encryption request has timed out, toleranceTime:{}, requestTime:{}, After decryption：{}", toleranceTime, requestTime, decryptBody);
                throw new EncryptRequestException("request timeout");
            }
        }

        this.body = new ByteArrayInputStream(decryptBody.getBytes());
        /*加密内容 30分钟后失效*/
        redisUtil.set("rsa:params:"+ content, 1, 6000);
        decryptHttpInputMessage.setBody(body);
        decryptHttpInputMessage.setHeaders(headers);
        return decryptHttpInputMessage;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public void setBody(InputStream body) {
        this.body = body;
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }


}
