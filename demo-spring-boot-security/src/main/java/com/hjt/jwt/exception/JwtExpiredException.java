package com.hjt.jwt.exception;

/**
 * Created by hjt on 2020/6/23.
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}
