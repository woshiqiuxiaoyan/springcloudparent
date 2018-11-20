package com.yan.springcloudribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yan.springcloudribbon.service.IComsumerServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>类名:ComsumerServiceImpl </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/18
 * 创建时间: 上午11:43
 */
@Service
public class ComsumerServiceImpl implements IComsumerServicce {

    private String urlPreix="http://hello-service";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 断路器只能在service 使用
     * @return
     */
    @Override
    @HystrixCommand (fallbackMethod = "hiError")
    public String helloComsumerService() {
        return restTemplate.getForEntity(urlPreix+"/comsumer",String.class).getBody();
    }

    public String  hiError(){
        return "返回断路错误。";
    }
}
