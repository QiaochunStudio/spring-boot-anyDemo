package com.hjt.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       try{
           // 去sleuth在的traceId（主id）设置在响应头 Response_No这个参数

           // 增加响应头的请求号
           HttpServletResponse httpServletResponse = (HttpServletResponse) response;
           String traceId = MDC.get("traceId");
           httpServletResponse.addHeader("Response_No", traceId);

           filterChain.doFilter(httpServletRequest, httpServletResponse);
       }finally {
           /**注意  最后要执行 MDC.remove（）这个方法移除 */
           MDC.remove("traceid");
       }

    }


}

