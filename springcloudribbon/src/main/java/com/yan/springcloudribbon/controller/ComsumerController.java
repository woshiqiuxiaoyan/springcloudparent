package com.yan.springcloudribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>类名:ComsumerController </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/7
 * 创建时间: 17:28
 */
@RestController
public class ComsumerController {


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping ("comsumer")
    public String helloComsumer() {
        return restTemplate.getForEntity("http://hello-service/hello",String.class).getBody();
    }

}
