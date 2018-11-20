package com.yan.springcloudfegin.service;

import com.yan.common.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>类名:ITestService </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/17
 * 创建时间: 下午10:38
 */
//入参和出参是否必须一致
@FeignClient ("hello-service")
public interface ITestService {
    @RequestMapping ("/comsumer")
    String comsumer();


    @RequestMapping ("/hello")
    String hello(@RequestParam(value="name") String name);

    @RequestMapping ("/getUserInfo")
    UserInfo getUserInfo();


    @RequestMapping ("/saveUserInfo")
    String saveUserInfo(@RequestBody UserInfo userInfo);
}
