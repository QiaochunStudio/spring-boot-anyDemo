package com.hjt.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hjt.constant.CacheConstants;
import com.hjt.domain.LoginUser;
import com.hjt.domain.SysUser;
import com.hjt.service.RedisService;
import com.hjt.text.Convert;
import com.hjt.utils.StringUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Objects;

/**
 * 权限获取工具类
 *
 * @author hjt
 */
@Component
public class SecurityUtils {

    private static RedisService redisService;

    static {
        redisService = SpringUtils.getBean(RedisService.class);
    }

    /**
     * 获取用户
     */
    public static SysUser getUser() {

        // 获取请求携带的令牌
        String token = getToken(ServletUtils.getRequest());
        if (StringUtils.isNotEmpty(token)) {
            LoginUser loginUser = (LoginUser) redisService.getCacheObject(CacheConstants.LOGIN_TOKEN_KEY + token);
            if (ObjectUtil.isNotNull(loginUser)) {
                return loginUser.getSysUser();
            }
        }
        return null;
    }

    /**
     * 获取用户
     */
    public static String getUsername() {
        SysUser user = getUser();
        if(ObjectUtil.isNotNull(user)){
            return ServletUtils.urlDecode(Objects.requireNonNull(user).getUserName());
        }
        return null;
    }


    /**
     * 获取用户
     */
    public static String getNickName() {
        return ServletUtils.urlDecode(Objects.requireNonNull(getUser()).getNickName());
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        SysUser user = getUser();
        if(ObjectUtil.isNotNull(user)){
            return Convert.toLong(Objects.requireNonNull(getUser()).getUserId());
        }
        return null;
    }

    /**
     * 获取请求token
     */
    public static String getToken() {
        return getToken(ServletUtils.getRequest());
    }

    /**
     * 根据request获取请求token
     */
    public static String getToken(HttpServletRequest request) {
        if (null != ServletUtils.getRequest()) {
            String token = ServletUtils.getRequest().getHeader(CacheConstants.HEADER);
            if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX)) {
                token = token.replace(CacheConstants.TOKEN_PREFIX, "");
            }
            return token;
        }
        return null;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    //用Base64中将byte[]数组转为字符串
    public static String getEncoder(byte[] encryptResult) {
        String encoded = Base64.getEncoder().encodeToString(encryptResult);
        return encoded;
    }

    public static void main(String[] args) {


    }

}
