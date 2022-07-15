package com.hjt.exception;

/**
 * 验证码错误异常类
 *
 * @author ljf
 */
public class CaptchaException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public CaptchaException(String msg)
    {
        super(msg);
    }
}
