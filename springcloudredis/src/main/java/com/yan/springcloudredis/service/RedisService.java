package com.yan.springcloudredis.service;

import com.yan.springcloudredis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 16:31
 * @Version:
 */
@Service
public class RedisService {
    @Cacheable(value = "userCache",keyGenerator = "wiselyKeyGenerator")
    public User findUser(String userId, String userName, String passWord,String phone){
        System.out.println("无缓存的时候调用这里");
        return new User(userId,userName,passWord,phone);
    }
   /* @Cacheable(value = "addresscache",keyGenerator = "wiselyKeyGenerator")
    public Address findAddress(Long id,String province,String city){
        System.out.println("无缓存的时候调用这里");
        return new Address(id,province,city);
    }*/
}
