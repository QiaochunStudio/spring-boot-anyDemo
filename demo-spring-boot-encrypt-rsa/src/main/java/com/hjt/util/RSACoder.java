package com.hjt.util;

/**
 * @author :hjt
 * @date : 2022/10/9
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RSACoder {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCODswgRYST1HWXdzoCAgRinlO6WW4PIxxkScF8TPjhdgWRAcoWoa2Ai9qQTg+pkccsFkZguAPeoyupHt2hrxaGV96JXGr2hcgboZejW3cvru1adO2Tq2JoH4/PCVqNQ6D78V5I6K678LhzMT4k+9/hvSrmZS714Z/LM7Xy7y1PvQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAI4OzCBFhJPUdZd3OgICBGKeU7pZbg8jHGRJwXxM+OF2BZEByhahrYCL2pBOD6mRxywWRmC4A96jK6ke3aGvFoZX3olcavaFyBuhl6Nbdy+u7Vp07ZOrYmgfj88JWo1DoPvxXkjorrvwuHMxPiT73+G9KuZlLvXhn8sztfLvLU+9AgMBAAECgYA/CS/pDhADbR51BLHX3D9H54utwCtHSkQ5+ESEPL+fhDh6HPooyrtrtCNbL1hh3U8tMAEwv9bvvyYLmKeuLDxo2yweBBU/zhgQPNGbztt4/2WFI4I08kq8ey9L4RBlUOq+gC6exW5nWH5xPUwxFKKNKVLSuS57IxG4lGvq61yEPQJBAOBy+adQEPhfbAzsr2S1hOwkaRIq35Ich3CD6+69yo0rFIqSVqZOdnwXyByXEVZqALo/RT6JeKSOOGU3KdwM48cCQQCiBuN721lMy8vAWYCLLnArVWYav73f3NMeegwa6wQSftFTA+hLOLZs5EKS4kPIW9mruhO2kJsO6nA8afaedOhbAkB/cim6TGdmcOBsslShbcTNRZt32mpaj+KEDBSC2rfR0t12FcQn6LO0oNhbC5inpcdF+jk6WlrrrWnuZxVYwuTvAkBlBUB+L7PMwt/FjMgtfwWmrvcbRGNBGTB0NwonGlKOqY0fYjdJ9xEecW7kn7g9Sq87d6fk0uIqeZcCw9pkz2UzAkAwp231Oicz9mPMqGzr/c1b3vQO9ykTG7M/OjCi041SrvaWQwF19GduoCCcqo6lnbbtyaboUI6DYZUPw8J+74Aw";


    /**
     * RSA Maximum Encrypted Plaintext Size
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA Maximum decrypted ciphertext size
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);
        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);
        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);
        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);
        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception{
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(String data, String key)
            throws Exception {
        return decryptByPrivateKey(decryptBASE64(data),key);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
//        int inputLen = data.length;
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        int offSet = 0;
//        byte[] cache;
//        int i = 0;
//        // Sectional Encryption of Data
//        while (inputLen - offSet > 0) {
//            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
//                cache = cipher.doFinal(data, offSet, MAX_DECRYPT_BLOCK);
//            } else {
//                cache = cipher.doFinal(data, offSet, inputLen - offSet);
//            }
//            out.write(cache, 0, cache.length);
//            i++;
//            offSet = i * MAX_DECRYPT_BLOCK;
//        }
//        byte[] decryptedData = out.toByteArray();
//        out.close();
//        return decryptedData;
        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data.getBytes());
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // Sectional Encryption of Data
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;

//        return cipher.doFinal(data);
    }

    /***
     * 分段加密
     * @param input
     * @param rsaPublicKey
     * @return
     */
    public static String rsaEncrypt(String input, String rsaPublicKey) {
        String result = "";
        try {
            // 将Base64编码后的公钥转换成PublicKey对象
            byte[] buffer = Base64Util.decode(rsaPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            // 加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] inputArray = input.getBytes();
            int inputLength = inputArray.length;
            System.out.println("加密字节数：" + inputLength);
            // 最大加密字节数，超出最大字节数需要分组加密
            int MAX_ENCRYPT_BLOCK = 117;
            // 标识
            int offSet = 0;
            byte[] resultBytes = {};
            byte[] cache = {};
            while (inputLength - offSet > 0) {
                if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                    offSet += MAX_ENCRYPT_BLOCK;
                } else {
                    cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                    offSet = inputLength;
                }
                resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
                System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
            }
            result = Base64Util.encode(resultBytes);
        } catch (Exception e) {
            System.out.println("rsaEncrypt error:" + e.getMessage());
        }
        System.out.println("加密的结果：" + result);
        return result;
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Key> keyMap)
            throws Exception {
        Key key = keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        Map<String, Key> keyMap = new HashMap(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
        return keyMap;
    }

    /***
     * byte数组转String
     * @param decrypt
     * @return
     */
    public static String exchangeStr(byte[] decrypt) {
        try {
            String result = new String(decrypt, "UTF-8");
            return result;
        } catch (UnsupportedEncodingException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, Key> keyMap = initKey();
        String publicKey = getPublicKey(keyMap);
        String privateKey = getPrivateKey(keyMap);
//
//        System.out.println(keyMap);
//        System.out.println("-----------------------------------");
//        System.out.println(publicKey);
//        System.out.println("-----------------------------------");
//        System.out.println(privateKey);
//        System.out.println("-----------------------------------");
        /*私钥加密*/
        byte[] encryptByPrivateKey = encryptByPrivateKey("TestBean(name=shuibo.cn, age=13, timestamp=1665651784191)".getBytes(),privateKey);
        String encode = Base64Util.encode(encryptByPrivateKey);
        System.out.println("encode："+encode);
        /*公钥加密*/
//        byte[] encryptByPublicKey = encryptByPublicKey("TestBean(name=shuibo.cn, age=13, timestamp=1665651784191)",publicKey);
//        System.out.println(new String(encryptByPrivateKey));
//        System.out.println("-----------------------------------");
//        System.out.println(new String(encryptByPublicKey));
//        System.out.println("-----------------------------------");
        /*用私钥加签名*/
//        String sign = sign(encryptByPrivateKey,privateKey);
//        System.out.println(sign);
//        System.out.println("-----------------------------------");
//        /*用公钥验签*/
//        boolean verify = verify(encryptByPrivateKey,publicKey,sign);
//        System.out.println(verify);
//        System.out.println("-----------------------------------");

        /*base64解密*/
        byte[] decode = Base64Util.decode(encode);
        /*用公钥解密*/
//        byte[] decryptByPublicKey = decryptByPublicKey(strData.getBytes(),publicKey);
        byte[] decodeByPublicKey = decryptByPublicKey(decode,publicKey);

        /*用私钥解密*/
//        byte[] decryptByPrivateKey = decryptByPrivateKey(decode,privateKey);
        System.out.println(new String(decodeByPublicKey));
        System.out.println("-----------------------------------");
//        System.out.println(new String(decryptByPrivateKey));

  }
}
