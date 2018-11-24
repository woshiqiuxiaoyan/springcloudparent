package com.yan.springcloudactuator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:Actouator </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/24
 * 创建时间: 14:54
 */
@RefreshScope
@RestController
public class ActouatorController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello world";
    }

    /**
     * 第一种取值方式
     */
    @Value (value="${yandaye}")
    private String yandaye;


    @RequestMapping("/yandaye")
    public String getEnv(){
        System.err.println("环境变量值："+yandaye);
        return yandaye;
    }

}
