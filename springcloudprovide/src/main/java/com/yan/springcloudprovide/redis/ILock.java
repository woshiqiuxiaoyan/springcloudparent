package com.yan.springcloudprovide.redis;

import com.yan.springcloudprovide.redis.util.LockCallback;
import com.yan.springcloudprovide.redis.util.LockCantObtainException;
import com.yan.springcloudprovide.redis.util.LockInsideExecutedException;
import com.yan.springcloudprovide.redis.util.LockRetryFrequency;

/**
 * <p>类名:ILock </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/12/7
 * 创建时间: 14:20
 */
public interface ILock {

    <T> T lock(String key,LockRetryFrequency frequency,int timeoutInSecond,long redisKeyExpireSeconds,LockCallback<T> lockCallback) throws LockInsideExecutedException, LockCantObtainException;
}
