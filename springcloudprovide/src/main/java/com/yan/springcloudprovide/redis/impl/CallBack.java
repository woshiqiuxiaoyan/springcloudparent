package com.yan.springcloudprovide.redis.impl;

import redis.clients.jedis.Jedis;

public interface CallBack<T> {

	T invoke(Jedis jedis);
}
