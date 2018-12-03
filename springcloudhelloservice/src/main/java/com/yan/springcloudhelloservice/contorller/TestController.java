package com.yan.springcloudhelloservice.contorller;

import com.yan.common.entity.UserInfo;
import com.yan.springcloudapi.service.ITestService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class TestController  {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/comsumer")
    public String  comsumer(){
        List<String> serviceIds =  discoveryClient.getServices();
        serviceIds. forEach((e)->{
            System.err.println(e);
        });


        return "helloService:"+serviceIds.toString();
    }


    @RequestMapping("/hello")
    public String  hello(@RequestParam String name){
        System.err.println("hello:"+name);
        return "hello:"+name;
    }

    @RequestMapping("/getUserInfo")
    public UserInfo  getUserInfo(){
      /*  UserInfo userInfo=new UserInfo();
        userInfo.setAge(123);
        userInfo.setGoodAt("什么都会");
        userInfo.setName("丘小燕");*/
        return this.userInfo;
    }



    private UserInfo userInfo;

    @RequestMapping("/saveUserInfo")
    public String saveUserInfo(@RequestBody UserInfo userInfo,HttpServletRequest request){

        String yandaye  = request.getHeader("yandaye");
        System.err.print("yandaye: "+yandaye);
        this.userInfo=userInfo;
        return "保存成功";
    }



}
