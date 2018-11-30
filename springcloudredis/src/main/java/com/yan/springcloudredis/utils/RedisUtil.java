package com.yan.springcloudredis.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Author: ChenBJ
 * @Description: Redis工具类
 * @Date: 2018/11/28 16:08
 * @Version:
 */
@Component
@Slf4j
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;


    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public String get(final String key) {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if(result==null){
            return null;
        }
        return result.toString();
    }

    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  boolean hmset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  Map<String,String> hmget(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean executeMulti(String key, List<Map<String, Object>> redisHmsets, String field) {
        boolean result = false;
        try {
            if(redisHmsets != null && redisHmsets.size() > 0) {
                log.info("*************同步Redis哈希值<开始>，共" + redisHmsets.size() + "条数*************");
                List<Map<String, Object>> tempList = new ArrayList<>();
                for(int i = 0; i*1000 < redisHmsets.size(); i++) {
                    if ((i + 1) * 1000 <= redisHmsets.size()) {
                        tempList = redisHmsets.subList(i * 1000, (i + 1) * 1000);
                    } else {
                        tempList = redisHmsets.subList(i * 1000, redisHmsets.size());
                    }
                    List<String> deletes = new ArrayList<>();
                    for (Map<String, Object> map : tempList) {
                        log.info("删除redis,key:" + key +  map.get(field));
                        deletes.add(key +  map.get(field));
                    }
                    List<Map<String, Object>> hmsets = new ArrayList<>(tempList);

                    redisTemplate.execute(new SessionCallback() {
                        @Override
                        public Object execute(RedisOperations redisOperations) throws DataAccessException {
                            redisOperations.multi();
                            redisOperations.delete(deletes);
                            for (Map<String, Object> map : hmsets) {
                                Map<String, String> hash = new HashMap<String, String>();
                                for (String keyTmp : map.keySet()) {
                                    hash.put(keyTmp, String.valueOf(map.get(keyTmp)));
                                }
                                log.info("新增redis,key:" + (key + hash.get(field)) + ";value:" + hash.toString());
                                redisOperations.opsForHash().putAll(key + hash.get(field), hash);
                            }
                            List response = redisOperations.exec();
                            return response;
                        }
                    });
                }
                log.info("*************同步Redis哈希值<结束>，共" + redisHmsets.size() + "条数*************");
                result = true;
            }else {
                log.info("*************无数据，同步Redis哈希值<结束>*************");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
