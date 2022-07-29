package com.hjt.jwt.exception;

/**
 * Created by hjt on 2020/6/23.
 */
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}
