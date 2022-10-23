package com.hjt.annotation;

/**
 * @author :hjt
 * @date : 2022/10/21
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
    public @interface NoRepeatSubmit {

    /**
     * 设置请求锁定时间
     * @return
     */
    int lockTime() default 5;

    /***
     * redisson方法等待时间
     * @return
     */
    int waitTime() default 3;
}

