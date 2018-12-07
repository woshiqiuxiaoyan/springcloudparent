package com.yan.springcloudservice.redis.impl;

public interface GetDataCallBack<R> {

    int getExpiredTime();

    R invoke();
}
