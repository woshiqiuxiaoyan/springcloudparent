package com.yan.springcloudhelloservice.contorller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:TestController </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/11/7
 * 创建时间: 16:39
 */
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/hello")
    public String  hello(){
        List<String> serviceIds =  discoveryClient.getServices();
        serviceIds.forEach((e)->{
            System.err.println(e);
        });


        return serviceIds.toString();
    }

}
