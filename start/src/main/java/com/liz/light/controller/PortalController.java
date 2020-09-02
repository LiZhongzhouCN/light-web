package com.liz.light.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liz
 * @Description:
 * @date: 2020/5/29 19:28
 */
@Controller
@Api(value = "PortalController",tags = "首页")
@RequestMapping
public class PortalController {

    @ApiOperation("首页")
    @GetMapping({"/","/index","login"})
    public String index() {

        return "index";
    }
}
