package com.yan.springcloudprovide.contorller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:MyProvide </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/10/29
 * 创建时间: 17:27
 */
@RestController
public class MyProvide {


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/index")
    public String inex(){
        List<String> services = discoveryClient.getServices();
//        service discoveryClient.getInstances("springcloudprovide");
        services.forEach((e)->{
            System.err.println(e);
        });
        return "hello yan";
    }

}
