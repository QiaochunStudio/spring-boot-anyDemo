//package com.hjt.aspect;
//
///**
// * @author :hjt
// * @date : 2022/10/21
// */
//
//import com.eshop.api.ApiResult;
//import com.eshop.common.aop.NoRepeatSubmit;
//import com.eshop.common.util.RedisLock;
//import com.eshop.common.util.RequestUtils;
//import com.hjt.util.RedissonUtil;
//import com.hjt.util.ServletUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.UUID;
//
///**
// * 重复提交aop
// */
//@Aspect
//@Component
//@Slf4j
//public class RepeatSubmitAspect {
//
//    @Autowired
//    private RedissonUtil redissonUtil;
//
//    @Pointcut("@annotation(noRepeatSubmit)")
//    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
//    }
//
//    @Around("pointCut(noRepeatSubmit)")
//    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
//        int lockSeconds = noRepeatSubmit.lockTime();
//
//        HttpServletRequest request = ServletUtils.getRequest();
//        Assert.notNull(request, "request can not null");
//
//        String bearerToken = request.getHeader("Authorization");
//        String[] tokens = bearerToken.split(" ");
//        String token = tokens[1];
//        String path = request.getServletPath();
//        String key = getKey(token, path);
//        String clientId = getClientId();
//
//        boolean isSuccess = redissonUtil.tryLock(key, clientId, lockSeconds);
//        log.info("tryLock key = [{}], clientId = [{}]", key, clientId);
//
//        if (isSuccess) {
//            log.info("tryLock success, key = [{}], clientId = [{}]", key, clientId);
//            // 获取锁成功
//            Object result;
//
//            try {
//                // 执行进程
//                result = pjp.proceed();
//            } finally {
//                // 解锁
//                redisLock.releaseLock(key, clientId);
//                log.info("releaseLock success, key = [{}], clientId = [{}]", key, clientId);
//            }
//            return result;
//        } else {
//            // 获取锁失败，认为是重复提交的请求
//            log.info("tryLock fail, key = [{}]", key);
//            return  ApiResult.fail("重复请求，请稍后再试");
//        }
//    }
//
//    private String getKey(String token, String path) {
//        return token + path;
//    }
//
//    private String getClientId() {
//        return UUID.randomUUID().toString();
//    }
//
//}