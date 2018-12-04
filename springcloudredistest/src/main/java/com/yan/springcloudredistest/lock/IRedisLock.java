package com.yan.springcloudredistest.lock;

public interface IRedisLock {
    String lock(String lockKey,long lockTimeOut);
    void unLock(String key,String value);
}
