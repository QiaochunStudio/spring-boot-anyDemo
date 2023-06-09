package com.hjt.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Component
public class FeignClientsConfigurationCustom implements RequestInterceptor {
    /***
     * 前端传过来的全文日志id
     */
    private final static String REQUEST_NO = "request_no";
    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 此种方式是线程安全的
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        // 不为空时取出请求中的header 原封不动的设置到feign请求中
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            if (null != request) {
                //设置请求头
                requestTemplate.header(REQUEST_NO,"1234567890");
//                // 遍历设置 也可从request取出整个Header 写到RequestTemplate 中
//                Enumeration<String> headerNames = request.getHeaderNames();
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        String values = request.getHeader(name);
//                        requestTemplate.header(name, values);
//                    }
//                }
            }
        }
    }
}
