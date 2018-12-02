package com.yan.springcloudfegin.service.impl;

import com.yan.springcloudfegin.service.IProvideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>类名:ProvideServiceImpl </p>
 * <p>描述:断路器返回</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/12/2
 * 创建时间: 19:19
 */
@Component
public class ProvideServiceImpl implements IProvideService {

    private String hyStrixErrorInfo = "我是断路器，你挂了";

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public String testRedisLua() {
        logger.info(String.format("====断路器挂了%s","testRedisLua"));
        return hyStrixErrorInfo;
    }
}
