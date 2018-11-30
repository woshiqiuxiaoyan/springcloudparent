package com.yan.springcloudprovide.contorller;

import com.yan.common.constants.RedisConstants;
import com.yan.springcloudprovide.redis.IRedisClient;
import com.yan.springcloudprovide.service.UserService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Response;

/**
 * @ClassName UserController
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:11
 * @Version:
 */
@RestController
@RequestMapping ("/testBoot")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Autowired
    private IRedisClient redisClient;

    @RequestMapping ("getUser/{userId}")
    public String GetUser(@PathVariable ("userId") String userId) {
        return userService.query(userId).toString();
    }


    @RequestMapping ("/testRedis")
    public String testRedis() {
        redisClient.set(RedisConstants.Test.testName + "qiuxy",RedisConstants.NameSpace.SpringCloudParent,"丘小燕",1000,RedisConstants.TEST_DBINDEX);
        return "123";
    }

    @RequestMapping ("/testRedisPipline")
    public String testRedisPipline() {
        Map<String,Object> resultMap =  redisClient.pipelineWithResult(RedisConstants.TEST_DBINDEX,(pipeline,map) -> {
            pipeline.hset(RedisConstants.NameSpace.SpringCloudParent+ RedisConstants.Test.testHsetName,"age","25");
            Map<String,String> map1 = new HashMap<>(1 << 4);
            map1.put("name","qiuxy1");
            pipeline.hmset(RedisConstants.NameSpace.SpringCloudParent+RedisConstants.Test.testHsetName,map1);
            Response response= pipeline.hgetAll(RedisConstants.NameSpace.SpringCloudParent+RedisConstants.Test.testHsetName);
            map.put("result", response);
        });

        resultMap.forEach((k,v)->{
            logger.info("key :"+k+"  value: "+v);
        });

        return "123";
    }



    @RequestMapping ("/testRedisLua")
    public String testRedisLua() {




        return "123";
    }





}
