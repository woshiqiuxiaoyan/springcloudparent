package com.yan.springcloudservice.redis.util;

public interface GetDataCallBack<R> {
    int getExpiredTime();

    R invoke();
}
