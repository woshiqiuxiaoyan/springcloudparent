package com.yan.springcloudfegin.service;

import com.yan.springcloudfegin.service.impl.ProvideServiceImpl;
import com.yan.springcloudfegin.service.impl.TestServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>类名:IProvideService </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/12/2
 * 创建时间: 19:18
 */
@FeignClient (value = "springcloudprovide",fallback = ProvideServiceImpl.class)
public interface IProvideService {

    /**
     * it's work
     * @return
     */
    @RequestMapping("/testBoot/testRedisLua")
    String testRedisLua() ;


}
