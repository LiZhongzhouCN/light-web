package com.liz.light.resolve;

import com.liz.light.base.CommonResult;
import com.liz.light.base.ResultCode;
import com.liz.light.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


/**
 * @Author: liz
 * @Description:
 * @Date: 2019/8/26 19:12
 * @Modified By:
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {
    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return CommonResult.failed(ResultCode.UNKNOWN_EXCEPTION);
    }


    /**
     * 自定义业务逻辑异常的父类
     */
    @ExceptionHandler(ApiException.class)
    public CommonResult apiException(ApiException e) {
        log.error("业务异常:{}", e.getMessage());
        return CommonResult.failed(e.getCode()).msg(e.getMessage());
    }

    /**
     * 处理@Valid绑定注解的异常抛出
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg = "";
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            msg = fieldErrors.get(0).getDefaultMessage();
        }
        return CommonResult.failed(msg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String msg = "";
        if (constraintViolations.size()>0){
            msg = (constraintViolations.toArray(new ConstraintViolation[constraintViolations.size()]))[0].getMessage();
        }
        return CommonResult.failed(msg);
    }
}
