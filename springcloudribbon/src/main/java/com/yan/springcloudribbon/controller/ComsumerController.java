package com.yan.springcloudribbon.controller;

import com.yan.common.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    private String urlPreix="http://hello-service";


    @RequestMapping ("comsumer")
    public String helloComsumer() {
        return restTemplate.getForEntity(urlPreix+"/comsumer",String.class).getBody();
    }


    @RequestMapping ("hello")
    public String helloYan() {
        for (int i = 10; i > 0; i--) {
            restTemplate.getForEntity(urlPreix+"/hello?name={1}",String.class,"丘小燕").getBody();
        }
        return "try it";
    }


    @RequestMapping ("getUserEntity")
    public String getUserEntity() {
        ResponseEntity<UserInfo> repository = restTemplate.getForEntity("http://hello-service/getUserInfo",UserInfo.class);
        return "good job!" + repository.getBody();
    }

    @RequestMapping("saveUserInfo")
    public String saveUserInfo(){
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(11);
        userInfo.setGoodAt("什么都会");
        userInfo.setName("丘小燕");

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("yandaye","我是燕大爷");

        System.err.print("我是中文===");
        HttpEntity<UserInfo> httpEntity=new HttpEntity<>(userInfo,httpHeaders);
        String result = restTemplate.postForEntity(urlPreix+"/saveUserInfo?name={1}",httpEntity,String.class,"燕大爷").getBody();
        return result;

    }

}
