package com.hjt.annotation;

import java.lang.annotation.*;

/**
 * @author linxiao.xu
 */
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RowListener{

    // 数据源名称
    String dsName() default "";

    // 表名
    String[] tableNames() default {};

}
