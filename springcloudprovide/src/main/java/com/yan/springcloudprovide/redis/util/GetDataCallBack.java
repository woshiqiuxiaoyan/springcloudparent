package com.yan.springcloudprovide.redis.util;

public interface GetDataCallBack<R> {
    int getExpiredTime();

    R invoke();
}
