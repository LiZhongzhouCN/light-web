package com.liz.light.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: liz
 * @Description:
 * @Date: 2019/8/26 19:12
 * @Modified By:
 */
public class CommonException extends ApiException {
    private ExceptionCode exceptionCode;

    public CommonException(ExceptionCode exceptionCode) {
        super();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMsg();
        this.exceptionCode = exceptionCode;
    }

    @AllArgsConstructor
    @Getter
    public enum ExceptionCode {
        INTERFACE_CALL_FAIL(10001,"系统错误"),
        LIMIT_FAIL(10003,"限流失败"),
        LIMIT_EXCEPTION(10002,"抱歉，您访问的太频繁，请稍后再试！");
        private int code;
        private String msg;
    }
}
