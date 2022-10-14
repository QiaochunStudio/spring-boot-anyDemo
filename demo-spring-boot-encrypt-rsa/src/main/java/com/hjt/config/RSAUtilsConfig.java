package com.hjt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author :hjt
 * @date : 2022/10/13
 */
@ConfigurationProperties(prefix = "rsa.encrypt")
@Configuration
public class RSAUtilsConfig {
    /**
     * key算法
     */
    public String keyAlgorithm;
    /***
     * 签名算法
     */
    public String signatureAlgorithm;

    /***
     * 公钥
     */
    private String publicKey;

    /***
     * 私钥
     */
    private String privateKey;


    /***
     *
     */
    private String charset = "UTF-8";

    /***
     * 是否开启加密
     */
    private boolean open = true;

    /***
     *是否打印加解密log
     */
    private boolean showLog = true;


    /**
     * 是否开启时间戳检查
     */
    private boolean timestampCheck = false;


    public String getKeyAlgorithm() {
        return keyAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isShowLog() {
        return showLog;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

    public boolean isTimestampCheck() {
        return timestampCheck;
    }

    public void setTimestampCheck(boolean timestampCheck) {
        this.timestampCheck = timestampCheck;
    }
}
