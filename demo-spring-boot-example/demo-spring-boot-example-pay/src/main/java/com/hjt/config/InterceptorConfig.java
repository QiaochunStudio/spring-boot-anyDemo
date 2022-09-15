package com.hjt.config;

import com.hjt.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: csp1999
 * @Date: 2020/10/20/13:26
 * @Description: 拦截器配置
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /*
     * 将跨域拦截器交给spring容器托管
     * @return: com.haust.online_class.interceptor.CorsInterceptor
     * @create: 2020/10/21 12:20
     * @author: csp1999
     */
    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域拦截器注册(注意：跨域拦截器注册要放在最上方)
        registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
