package com.yan.springcloudservice.redis.util;

public interface LockCallback<T> {
    T handleObtainLock();

    T handleNotObtainLock() throws LockCantObtainException;

    T handleException(LockInsideExecutedException var1) throws LockInsideExecutedException;
}
