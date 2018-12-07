package com.yan.springcloudservice.redis.exception;

/**
 * redis客户端异常
 */
public class RedisClientException extends RuntimeException {

    /**
     */
    private static final long serialVersionUID = -4629579849260670181L;

    /**
     * 构造方法
     * 
     * @param msg 异常信息
     */
    public RedisClientException(String msg) {
        super(msg);
    }

    /**
     * 构造方法
     * 
     * @param exception 异常原因
     */
    public RedisClientException(Throwable exception) {
        super(exception);
    }

    /**
     * 构造方法
     * 
     * @param mag 异常信息
     * @param exception 异常原因
     */
    public RedisClientException(String mag,Exception exception) {
        super(mag, exception);
    }
}
