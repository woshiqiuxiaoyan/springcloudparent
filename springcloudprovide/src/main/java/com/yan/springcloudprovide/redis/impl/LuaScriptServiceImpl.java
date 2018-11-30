package com.yan.springcloudprovide.redis.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yan.common.constants.CommonErrorCodeEnum;
import com.yan.common.constants.RedisConstants;
import com.yan.common.exception.BizException;
import com.yan.springcloudprovide.redis.ILuaScriptService;
import com.yan.springcloudprovide.redis.IRedisClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
 * @Author  qxy
 * @Date  2018/11/30 20:19
 * @Description  
 * @param  
 * @return  
 */
@Service ("luaScriptService")
public class LuaScriptServiceImpl implements ILuaScriptService {

    private static Logger logger = LoggerFactory.getLogger(LuaScriptServiceImpl.class);

    @Autowired
    private IRedisClient redisClient;

    @Override
    public void eval(String luaKey,List<String> keys,List<String> args) {
        executeScript(luaKey,keys,args);
    }

    @Override
    public String eval2string(String luaKey,List<String> keys,List<String> args) {
        Object obj = executeScript(luaKey,keys,args);
        if (null == obj) {
            return null;
        }
        return (String)obj;
    }

    @Override
    public Map<String,String> eval2map(String luaKey,List<String> keys,List<String> args) {
        Object obj = executeScript(luaKey,keys,args);
        if (null == obj) {
            return null;
        }
        List<String> list = (List)obj;
        Map<String,String> res = new HashMap<>();
        for (int i = 0; i < list.size(); i += 2) {
            res.put(list.get(i),list.get(i + 1));
        }
        return res;
    }

    @Override
    public <T> T eval2bean(String luaKey,List<String> keys,List<String> args,Class<T> clz) {
        Object obj = executeScript(luaKey,keys,args);
        if (null == obj) {
            return null;
        }
        return JSONObject.parseObject(String.valueOf(obj),clz);
    }

    @Override
    public <T> List<T> eval2list(String luaKey,List<String> keys,List<String> args,Class<T> clz) {
        Object obj = executeScript(luaKey,keys,args);
        if (null == obj) {
            return null;
        }
        return JSONArray.parseArray(String.valueOf(obj),clz);
    }

    private Object executeScript(String luaKey,List<String> keys,List<String> args) {
        if (null == keys) {
            keys = new ArrayList<>();
        }
        if (null == args) {
            args = new ArrayList<>();
        }
        String sha = redisClient.get(luaKey,RedisConstants.DEFAULT_NAME_SPACE + RedisConstants.COLON + RedisConstants
                .LUA_KEY,null,RedisConstants.DEFAULT_DBINDEX);
        if (StringUtils.isBlank(sha) || !redisClient.scriptExists(sha)) {
            logger.error(String.format("lua key:%s not config in redis",luaKey));
            sha = loadLocalScript(luaKey);
            if (StringUtils.isBlank(sha)) {
                throw new BizException(CommonErrorCodeEnum.EXECUTE_LUA_SCRIPT_FAIL.getCode(),CommonErrorCodeEnum
                        .EXECUTE_LUA_SCRIPT_FAIL.getMessage());
            }
            logger.info(String.format("new lua sha1:%s",sha));
            redisClient.set(luaKey,RedisConstants.DEFAULT_NAME_SPACE + RedisConstants.COLON + RedisConstants.LUA_KEY,
                    sha,-1,RedisConstants.DEFAULT_DBINDEX);
        }
        return redisClient.evalsha(sha,keys,args);
    }


    /**
     * //todo 只做加载脚本，是否可以合在一起呢 加载和执行
     * @param luaKey
     * @return
     */
    private String loadLocalScript(String luaKey) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("lua/" + luaKey + ".lua");
        if (null != is) {
            String line = "";
            BufferedReader in = null;
            StringBuilder sb = new StringBuilder();
            try {
                in = new BufferedReader(new InputStreamReader(is));
                line = in.readLine();
                while (line != null) {
                    sb.append(line).append("\n");
                    line = in.readLine();
                }
                String script = sb.toString();
                logger.info(script);
                return redisClient.scriptLoad(script);
            } catch (Exception e) {
                logger.error("read lua script file fail",e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        return null;
    }


}
