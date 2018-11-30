package com.yan.springcloudprovide.redis.impl;

public interface GetDataCallBack<R> {

    int getExpiredTime();

    R invoke();
}
