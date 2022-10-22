package com.hjt.annotation;

/**
 * @author :hjt
 * @date : 2022/10/21
 *  * 自定义防重提交
 */
import java.lang.annotation.*;
@Documented
@Target(ElementType.METHOD)//可以用在方法上
@Retention(RetentionPolicy.RUNTIME)//保留到虚拟机运行时,可通过反射获取
public @interface RepeatSubmit {
    /**
     * 防重提交，支持两种，一个是方法参数，一个是令牌
     */
    enum Type { PARAM, TOKEN }
    /**
     * 默认防重提交，是方法参数
     * @return
     */
    Type limitType() default Type.PARAM;
    /**
     * 加锁过期时间，默认是5秒
     * @return
     */
    long lockTime() default 5;
}
