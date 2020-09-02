package com.liz.light.controller;

import com.liz.light.aop.ServiceLimit;
import com.liz.light.base.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/10 10:10
 */
@RestController
public class TestController {

    @ServiceLimit(limitType = ServiceLimit.LimitType.IP)
    @GetMapping("/test")
    public CommonResult test(){
        return CommonResult.success();
    }

}
