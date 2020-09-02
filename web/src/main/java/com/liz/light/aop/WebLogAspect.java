package com.liz.light.aop;

import cn.hutool.json.JSONUtil;
import com.liz.light.base.WebLog;
import com.liz.light.util.IPUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 统一日志处理切面
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.liz.light.*controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        WebLog webLog = new WebLog();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation log = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(log.value());
        }
        long endTime = System.currentTimeMillis();
        webLog.setIp(IPUtils.getIpAddr());
        webLog.setMethod(request.getMethod());
        webLog.setParameter(joinPoint.getArgs());
        webLog.setResult(result);
        webLog.setSpendTime((int) (endTime - startTime));
//        webLog.setStartTime(startTime);
//        webLog.setUri(request.getRequestURI());
        webLog.setUrl(request.getRequestURL().toString());
        log.info("{}", JSONUtil.parse(webLog));
        return result;
    }

}
