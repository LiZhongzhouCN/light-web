package com.liz.light.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: liz
 * @Description:
 * @Date: 2019/8/26 19:12
 * @Modified By:
 */
public class BusinessException extends ApiException {
    private ExceptionCode exceptionCode;

    public BusinessException(ExceptionCode exceptionCode) {
        super();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMsg();
        this.exceptionCode = exceptionCode;
    }

    @AllArgsConstructor
    @Getter
    public enum ExceptionCode {
        TOKEN_HEAD_EXCEPTION(30001,"token header is null"),
        TOKEN_CHECK_FAIL(30001,"token check fail");

        private int code;
        private String msg;
    }
}
