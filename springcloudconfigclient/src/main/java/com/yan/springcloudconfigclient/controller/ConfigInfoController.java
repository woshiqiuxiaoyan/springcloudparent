package com.yan.springcloudconfigclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:ConfigInfoController </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/10/29
 * 创建时间: 13:40
 */
@RefreshScope
@RestController
public class ConfigInfoController {

    /**
     * 第一种取值方式
     */
   /* @Value(value="${env}")
    private String env;


    @RequestMapping("/env")
    public String getEnv(){
        System.err.println("环境变量值："+env);
        return env;
    }*/


    /**
     * 第二种取值方式
     */
    @Autowired
    private Environment environment;

    @RequestMapping("/from")
    public String getFrom(){
        System.err.println( environment.getProperty("name"));
        System.out.println("中文");
        System.out.println(environment.getProperty("yandaye"));
        return "hello :"+environment.getProperty("env");
    }


}
