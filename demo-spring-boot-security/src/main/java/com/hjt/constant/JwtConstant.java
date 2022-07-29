package com.hjt.constant;



/***
 * @author hjt
 * jwt持久化
 */
public class JwtConstant {
    /**
     * jwt第一层加密秘钥
     */
    public static final String SECRET = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArTH6fuGby5ueY/qJFXqHScfboVztXItTFMpXzFItYluRwhfpeiD5jhUihN5VNS+gsBJYpQmi3OZuNP7MYGap0LpUbqomgmWoVKV9qjplGoeFdenE1rfARzeQTNkBkRmJC36Ml48ybKdr4qV7OnVBvNYMRQhoWRLOrxNWGCPwsnT48/lSxRIvTREUx5PvY7/4hjtK+jw9shKZgfnWRokm4SWTOxHYuoKe4R99jfzjrwfVNZ11jt2tu3h8/J418ZhZhbKF+v+cuS+qyQkWnpjzCKS5kq3iL2xYD8cpE+98QTefzAa2iJa1zf/FPoINeRTbeIXVgqD7kV+nZ6ZQpvlHKwIDAQAB";


    /***
     *  7天的秒数
     */
    public static final Long EXPIRATION = 604800L;


    /***
     * jwt第二层加密放redis中的前缀
     */
    public static final String SECRET_KEY_ID = "secret_key:";






}

