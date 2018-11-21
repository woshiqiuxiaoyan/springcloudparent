package com.yan.springcloudfegin.service.impl;

import com.yan.common.entity.UserInfo;
import com.yan.springcloudfegin.service.ITestService;
import org.springframework.stereotype.Component;

/**
 * <p>类名:TestServiceImpl </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/21
 * 创建时间: 13:38
 */
@Component
public class TestServiceImpl implements ITestService {

    private String hyStrixErrorInfo="我是断路器，你挂了";

    @Override
    public String comsumer() {
        return hyStrixErrorInfo;
    }

    @Override
    public String hello(String name) {
        return null;
    }

    @Override
    public UserInfo getUserInfo() {
        System.out.println(hyStrixErrorInfo);
        return null;
    }

    @Override
    public String saveUserInfo(UserInfo userInfo) {
        return hyStrixErrorInfo;
    }
}
