package com.yan.springcloudprovide.redis.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

public class DefaultClientImpl {

    private final Logger logger = LoggerFactory.getLogger(DefaultClientImpl.class);

    private static String DOT = ".";

    private static String COLON = ":";
    /**
     * 5000 constant
     */
    private static final int FIVE = 5000;
    @Autowired
    protected JedisPool jedisPool;


    public String toJSONString(String key,Object object) {
        String res = JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
        logger.debug("key:" + key + ",toJSONString length:" + res.length());
        return res;
    }

    public <T> T parseObject(String key,String text,Class<T> clazz) {
        logger.debug("key:" + key + ",parseObject length:" + text.length());
        return JSON.parseObject(text,clazz);
    }

    public <T> T parseObject(String key,String text,TypeReference<T> type) {
        logger.debug("key:" + key + ",parseObject length:" + text.length());
        return JSON.parseObject(text,type);
    }


    protected <T> T execute(CallBack<T> callback) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return callback.invoke(jedis);
        } catch (JedisException e) {
            logger.error("jedis pool get resource error:{}",e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return null;
    }

    protected <R> R performFunction(String entrykey,CallBack<R> callBack) {
        return execute(callBack);
    }


    public List<String> getKeyByNamespace(List<String> bizkeys,String nameSpace) {
        List<String> keys = new ArrayList<String>();
        String key = null;
        for (String bizkey : bizkeys) {
            key = getKeyByNamespace(bizkey,nameSpace);
            keys.add(key);
        }
        return keys;
    }

    public String getKeyByNamespace(String key,String nameSpace) {
        if (StringUtils.isNotBlank(nameSpace)) {
            return nameSpace + COLON + key;
        }
        return key;
    }

    public String[] getKeyByNamespace(String[] bizkeys,String nameSpace) {
        String[] paramByte = null;
        if (bizkeys != null && bizkeys.length > 0) {
            paramByte = new String[bizkeys.length];
            for (int i = 0; i < bizkeys.length; i++) {
                paramByte[i] = getKeyByNamespace(bizkeys[i],nameSpace);
            }
        }
        return paramByte;
    }

    public String[] smapToArray(Map<String,String> map,String nameSpace) {
        String[] paramByte = null;
        if (map != null && map.size() > 0) {
            paramByte = new String[map.size() * 2];
            Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
            int index = 0;
            while (it.hasNext()) {
                Map.Entry<String,String> entry = it.next();
                paramByte[index++] = getKeyByNamespace(entry.getKey(),nameSpace);
                paramByte[index++] = entry.getValue();
            }
        }
        return paramByte;
    }


    public String[] getKeyArrByNamespace(List<String> bizkeys,String nameSpace) {
        if (CollectionUtils.isEmpty(bizkeys)) {
            return null;
        }
        String[] keys = new String[bizkeys.size()];
        String key = null;
        for (int i = 0; i < bizkeys.size(); i++) {
            String bizkey = bizkeys.get(i);
            key = getKeyByNamespace(bizkey,nameSpace);
            keys[i] = key;
        }
        return keys;
    }

    public String[] getKeyByNamespace(Map map,String nameSpace) {
        String[] paramByte = null;
        if (map != null && map.size() > 0) {
            paramByte = new String[map.size()];
            Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
            int index = 0;
            while (it.hasNext()) {
                Map.Entry<String,String> entry = it.next();
                paramByte[index++] = getKeyByNamespace(entry.getKey(),nameSpace);
            }
        }
        return paramByte;
    }

    public String getBizKeyWithNS(String keySplit,String nameSpace) {
        if (!StringUtils.isBlank(nameSpace)) {
            if (keySplit.indexOf(nameSpace) != -1) {
                return keySplit.substring(nameSpace.length() + 1,keySplit.length());
            }
        }
        return keySplit;
    }

    public byte[] objectToByteArray(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ObjectOutputStream os = null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(FIVE);
        os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;
    }

    public Object byteArrayToObject(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bis));
            obj = ois.readObject();
            bis.close();
            ois.close();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return obj;
    }

    public long nextDelayTime(int interval) {
        long now = System.currentTimeMillis() / 1000;
        long next = (now / interval + 1) * interval;
        long delay = next - now;
        if (delay <= 0) {
            return interval - delay;
        } else {
            return delay;
        }
    }
}