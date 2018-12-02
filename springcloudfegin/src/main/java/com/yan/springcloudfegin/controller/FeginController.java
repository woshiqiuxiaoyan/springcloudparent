package com.yan.springcloudfegin.controller;

import com.yan.common.entity.UserInfo;
import com.yan.springcloudfegin.service.IProvideService;
import com.yan.springcloudfegin.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.rmi.runtime.Log;

/**
 * <p>类名:FeginController </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/17
 * 创建时间: 下午10:25
 */
@RestController
public class FeginController {


    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ITestService testService;
    @Autowired
    private IProvideService provideService;

    @Autowired
    private RestTemplate restTemplate;

    private String urlPreix="http://hello-service";

    @RequestMapping ("/comsumer1")
    String comsumer1() {
        return restTemplate.getForObject(urlPreix+"/comsumer",String.class);
    }


    @RequestMapping ("/comsumer")
    String comsumer() {
        return testService.comsumer();
    }


    @RequestMapping ("/hello")
    String hello() {
        return testService.hello("燕大人");
    }

    @RequestMapping ("/getUserInfo")
    UserInfo getUserInfo(){
        return testService.getUserInfo();
    }


    @RequestMapping ("/saveUserInfo")
    String saveUserInfo(){
        UserInfo userInfo=new UserInfo();
        userInfo.setName("111");
        userInfo.setGoodAt("yes sir ");
        return testService.saveUserInfo(userInfo);
    }


    @RequestMapping("/testRedisLua")
    public String testRedisLua(){
        logger.info("======testRedisLua====");
        return provideService.testRedisLua();
    }

}
