package com.yan.springcloudredis.controller;

import com.yan.springcloudredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName RedisController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 16:32
 * @Version:
 */
@Controller
public class RedisController {
    @Autowired
    RedisService redisService;
    @RequestMapping("/test")
    @ResponseBody
    public String putCache(){
        redisService.findUser("1l","wang","123","12431");
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return "ok";
    }
}
