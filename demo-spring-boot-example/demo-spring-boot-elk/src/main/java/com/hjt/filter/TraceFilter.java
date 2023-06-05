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
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // "traceId" 正常来说这个应该是前端传给后端得 这个只是作为后端demo得演示
        String requestNo = httpServletRequest.getHeader("request_no");
        System.out.println("输出："+requestNo);
        MDC.put("traceid", getTraceId());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String uniqueId = timestamp + uuid.toString().replace("-", "");
        return uniqueId;
    }
}

