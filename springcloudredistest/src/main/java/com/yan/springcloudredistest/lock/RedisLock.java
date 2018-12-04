package com.yan.springcloudredistest.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @ClassName RedisLock
 * @Author: ChenBJ
 * @Description: redis锁
 * @Date: 2018/12/4 17:21
 * @Version:
 */
@Slf4j
@Component
public class RedisLock implements IRedisLock{
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * @Author: ChenBJ
     * @Description: 加锁
     * @Date: 2018/12/4 17:25
     * @Param: lockKey 键
     * @Param: lockTimeOut 超时时间(单位:秒)
     * @return:
     */
    @Override
    public String lock(String lockKye,long lockTimeOut){
        String lockValue = String.valueOf(Long.parseLong(String.valueOf(currtTimeFromRedis())) + lockTimeOut * 1000);
        //setnx命令
        if (redisTemplate.opsForValue().setIfAbsent(lockKye,lockValue)){
            log.info("加锁成功!");
            return lockValue;
        }

        //避免死锁
        String currentValue = String.valueOf(redisTemplate.opsForValue().get(lockKye));

        //判断过期锁
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < (Long)currtTimeFromRedis()){
            //返回value再设置新的value
            String oldValues = String.valueOf(redisTemplate.opsForValue().getAndSet(lockKye,lockValue));

            if (!StringUtils.isEmpty(oldValues) && oldValues.equals(currentValue)){
                log.info("加锁成功");
                return lockValue;
            }
        }
        return null;
    }

    /**
     * @Author: ChenBJ
     * @Description: 解锁
     * @Date: 2018/12/4 17:46
     * @Param: key 键
     * @Param: value 当前时间 +超时时间
     * @return:
     */
    @Override
    public void unLock(String key, String value) {
        try {
            String currentValue = String.valueOf(redisTemplate.opsForValue().get(key));
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(key)){
                redisTemplate.opsForValue().getOperations().delete(key);
                log.info("解锁成功!");
            }
        } catch (Exception e){
            log.error("redis解锁异常,{}",e);
        }
    }

    //获取redis服务器时间
    public Object currtTimeFromRedis(){
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }
}
