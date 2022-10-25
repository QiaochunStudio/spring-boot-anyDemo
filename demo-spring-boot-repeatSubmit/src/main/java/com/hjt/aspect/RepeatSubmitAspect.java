package com.hjt.aspect;
import com.hjt.annotation.NoRepeatSubmit;
import com.hjt.myException.BaseException;
import com.hjt.util.RedisUtil;
import com.hjt.util.ServletUtils;
import com.hjt.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
/**
 * @author :hjt
 * @date : 2022/10/21
 * 重复提交aop
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {


    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(noRepeatSubmit)")
    public void pointCut(NoRepeatSubmit noRepeatSubmit) {
    }

    @Around("pointCut(noRepeatSubmit)")
    public Object around(ProceedingJoinPoint pjp, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        int lockSeconds = noRepeatSubmit.lockTime();

        HttpServletRequest request = ServletUtils.getRequest();
        Assert.notNull(request, "request can not null");

        String bearerToken = request.getHeader("Authorization");
        String requestUUID = request.getHeader("requestUUID");
        if (StringUtils.isBlank(requestUUID)) {
            throw new BaseException("防止重复提交", "602", "requestUUID为空");
        }
        String path = request.getServletPath();
//        String key = getKey(token, path);
//        String clientId = getClientId();

        /***
         * 加锁  key为 路径+token+前端传过来的key
         */
        String resultKey = path + ":" + bearerToken + ":" + requestUUID;
        if (redisUtil.hasKey(resultKey)) {
            throw new BaseException("防止重复提交", "603", "出现重复提交");
        }
        /*24小时过期*/
        redisUtil.set(resultKey, 1, lockSeconds);

        // 获取锁成功
        log.info(" resultKey = [{}], requestUUID = [{}]", resultKey, requestUUID);
        Object result;
        result = pjp.proceed();
        return result;
    }

    private String getKey(String token, String path) {
        return token + path;
    }

    private String getClientId() {
        return UUID.randomUUID().toString();
    }

}