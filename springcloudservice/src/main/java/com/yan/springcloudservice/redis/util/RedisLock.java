package com.yan.springcloudservice.redis.util;

import com.yan.springcloudservice.redis.ILock;
import com.yan.springcloudservice.redis.IRedisClient;
import com.yan.springcloudservice.springutil.SpringContextUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>类名:RedisLock </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2018/12/5
 * 创建时间: 15:47
 */

@Data
public class RedisLock implements ILock {

    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);
    private static final String LOCK_NAMESPACE = "REDIS_LOCK";
    private IRedisClient redisClient;

    public RedisLock() {
        if (null == redisClient) {
            redisClient = SpringContextUtil.getBean(IRedisClient.class);
        }
    }


    /**
     * @param lockTime 锁定失效时间
     */

    public <T> T lock(String lockForDoTask,int timeoutInSecond,long lockTime,LockCallback<T> lockCallback) throws LockInsideExecutedException, LockCantObtainException {
        return this.lock(lockForDoTask,LockRetryFrequency.NORMAL,timeoutInSecond,lockTime,lockCallback);
    }

    @Override
    public <T> T lock(String key,LockRetryFrequency frequency,int timeoutInSecond,long redisKeyExpireSeconds,LockCallback<T> lockCallback) throws LockInsideExecutedException, LockCantObtainException {
        long curentTime = System.currentTimeMillis();
        long expireMillisSecond = curentTime + redisKeyExpireSeconds * 1000L;
        /**
         * 等待超时时间
         */
        long timeoutExprieTime = curentTime + (long)(timeoutInSecond * 1000) - (long)frequency.getRetrySpan();
        /**
         * 重试次数= 总等待锁时间 /重试间隔
         */
        int retryCount = timeoutInSecond * 1000 / frequency.getRetrySpan();
        Long redisCurrentTime = this.redisClient.getTime();

        for (int i = 0; i < retryCount; ++i) {
            /**
             * setnx  1 success 0 fail
             */
            if (this.redisClient.setnx(key,"LOCK",String.valueOf(expireMillisSecond),0).longValue() == 1L) {

                logger.debug("obtain the lock: {},  at {} retry",key,i);

                T var19;
                try {
                    this.redisClient.expireAt(key,"LOCK",redisCurrentTime.longValue() + redisKeyExpireSeconds,0);
                    /**
                     * 获得锁后处理请求
                     */
                    var19 = lockCallback.handleObtainLock();
                } catch (Exception var24) {
                    LockInsideExecutedException ie = new LockInsideExecutedException(var24);
                    var19 = lockCallback.handleException(ie);
                } finally {
                    logger.debug("release lock{}",key);
                    this.redisClient.del(key,"LOCK",0);
                }
                return var19;
            }
            if (System.currentTimeMillis() >= timeoutExprieTime) {
                logger.debug("do not obtain the lock: {},and has gone out of time,do not need to try again",key);
                break;
            }

            logger.debug("do not obtain the lock: {},  at {} retry",key,i);
            try {
                Thread.sleep((long)frequency.getRetrySpan());
            } catch (InterruptedException var23) {
                logger.error("Interrupte exception",var23);
            }
        }

        String expireSpecifiedInString = this.redisClient.get(key,"LOCK",null,0);
        if (expireSpecifiedInString != null) {
            long expireSpecified = Long.valueOf(expireSpecifiedInString).longValue();
            if (curentTime > expireSpecified) {
                logger.warn("detect the task lock is expired, key: {}, expireSpecified:{}, currentTime:{}",new Object[] {key,expireSpecified,curentTime});
                this.redisClient.del(key,"LOCK",0);
            }
        }

        T r = lockCallback.handleNotObtainLock();
        return r;
    }

}



