package com.liz.light.aop;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import com.liz.light.exception.CommonException;
import com.liz.light.util.IPUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 限流 AOP
 */
@Aspect
@Component
public class LimitAspect {

    //根据IP分不同的令牌桶, 每天自动清理缓存
    private static LoadingCache<String, RateLimiter> caches = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String key) {
                    // 新的IP初始化 每秒只发出6个令牌
                    return RateLimiter.create(1);
                }
            });

    //Service层切点  限流
    @Pointcut("@annotation(com.liz.light.aop.ServiceLimit)")
    public void ServiceAspect() {

    }

    @Around("ServiceAspect()")
    public  Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServiceLimit limitAnnotation = method.getAnnotation(ServiceLimit.class);
        ServiceLimit.LimitType limitType = limitAnnotation.limitType();
        String key = limitAnnotation.key();
        Object obj;
            if(limitType.equals(ServiceLimit.LimitType.IP)){
                key = IPUtils.getIpAddr();
            }
        RateLimiter rateLimiter = null;
        try {
            rateLimiter = caches.get(key);
        } catch (ExecutionException e) {
            throw new CommonException(CommonException.ExceptionCode.LIMIT_FAIL);
        }
        Boolean flag = rateLimiter.tryAcquire();
            if(flag){
                try {
                    obj = joinPoint.proceed();
                } catch (Throwable throwable) {
                    throw new CommonException(CommonException.ExceptionCode.INTERFACE_CALL_FAIL);
                }
            }else{
                throw new CommonException(CommonException.ExceptionCode.LIMIT_EXCEPTION);
            }
            return obj;
    }
}