package com.yan.springcloudredis.controller;

import com.yan.springcloudredis.service.RedisService;
import com.yan.springcloudredis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RedisController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 16:32
 * @Version:
 */
@RestController
public class RedisController {


    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
    RedisService redisService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping ("/redistest")
    public String putCache() {
        redisUtil.set("qiuxy","123");
        return "ok";
    }
}
