package com.hjt.annotation;

import com.hjt.advice.EncryptRequestBodyAdvice;
import com.hjt.advice.EncryptResponseBodyAdvice;
import com.hjt.config.RSACoderConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author:hjt
 * DateTime:2019/4/9 16:44
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({RSACoderConfig.class,
        EncryptResponseBodyAdvice.class,
        EncryptRequestBodyAdvice.class})
public @interface EnableSecurity{

}
