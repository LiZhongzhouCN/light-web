package com.liz.light.controller;

import com.liz.light.base.CommonResult;
import com.liz.light.service.FaceIndexSearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liz
 * @Description:
 * @date: 2020/5/29 17:38
 */
@RestController
@Api(value = "EsSearchController",tags = "Es查询")
@RequestMapping("/es")
public class EsSearchController {

    @Autowired
    private FaceIndexSearchService faceIndexSearchService;

    @ApiOperation("统计")
    @GetMapping("/count")
    public CommonResult refreshToken(HttpServletRequest request) {

        return CommonResult.success(faceIndexSearchService.getCount());
    }

}
