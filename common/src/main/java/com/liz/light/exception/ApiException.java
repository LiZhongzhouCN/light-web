package com.liz.light.exception;

import lombok.Getter;

/**
 * @Author: liz
 * @Description:所有的异常类请继承该类
 * @Date: 2019/8/26 19:12
 * @Modified By:
 */
@Getter
public class ApiException extends RuntimeException {

    protected long code;
    protected String message;

    public ApiException() {

    }

    public ApiException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
