package com.hjt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author:hjt
 * DateTime:2019/4/9
 **/
@ConfigurationProperties(prefix = "rsa.encrypt")
@Configuration
public class SecretKeyConfig{

    /***
     * rsa私钥
     */
    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI4OzCBFhJPUdZd3OgICBGKeU7pZbg8jHGRJwXxM+OF2BZEByhahrYCL2pBOD6mRxywWRmC4A96jK6ke3aGvFoZX3olcavaFyBuhl6Nbdy+u7Vp07ZOrYmgfj88JWo1DoPvxXkjorrvwuHMxPiT73+G9KuZlLvXhn8sztfLvLU+9AgMBAAECgYA/CS/pDhADbR51BLHX3D9H54utwCtHSkQ5+ESEPL+fhDh6HPooyrtrtCNbL1hh3U8tMAEwv9bvvyYLmKeuLDxo2yweBBU/zhgQPNGbztt4/2WFI4I08kq8ey9L4RBlUOq+gC6exW5nWH5xPUwxFKKNKVLSuS57IxG4lGvq61yEPQJBAOBy+adQEPhfbAzsr2S1hOwkaRIq35Ich3CD6+69yo0rFIqSVqZOdnwXyByXEVZqALo/RT6JeKSOOGU3KdwM48cCQQCiBuN721lMy8vAWYCLLnArVWYav73f3NMeegwa6wQSftFTA+hLOLZs5EKS4kPIW9mruhO2kJsO6nA8afaedOhbAkB/cim6TGdmcOBsslShbcTNRZt32mpaj+KEDBSC2rfR0t12FcQn6LO0oNhbC5inpcdF+jk6WlrrrWnuZxVYwuTvAkBlBUB+L7PMwt/FjMgtfwWmrvcbRGNBGTB0NwonGlKOqY0fYjdJ9xEecW7kn7g9Sq87d6fk0uIqeZcCw9pkz2UzAkAwp231Oicz9mPMqGzr/c1b3vQO9ykTG7M/OjCi041SrvaWQwF19GduoCCcqo6lnbbtyaboUI6DYZUPw8J+74Aw";

    /***
     * rsa公钥
     */
    private String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCODswgRYST1HWXdzoCAgRinlO6WW4PIxxkScF8TPjhdgWRAcoWoa2Ai9qQTg+pkccsFkZguAPeoyupHt2hrxaGV96JXGr2hcgboZejW3cvru1adO2Tq2JoH4/PCVqNQ6D78V5I6K678LhzMT4k+9/hvSrmZS714Z/LM7Xy7y1PvQIDAQAB";

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

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
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
