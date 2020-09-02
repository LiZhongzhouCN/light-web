package com.liz.light.aop;

import java.lang.annotation.*;

/**
 * 自定义注解 限流
 * 爪哇笔记 https://blog.52itstyle.vip
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public  @interface ServiceLimit {
    /**
     * 描述
     */
    String description()  default "";

    /**
     * key
     */
    String key() default "";

    /**
     * 类型
     */
    LimitType limitType() default LimitType.CUSTOMER;

    enum LimitType {
        /**
         * 自定义key
         */
        CUSTOMER,
        /**
         * 根据请求者IP
         */
        IP
    }
}