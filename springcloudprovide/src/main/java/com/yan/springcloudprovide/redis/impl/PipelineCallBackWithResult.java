package com.yan.springcloudprovide.redis.impl;

import java.util.Map;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public  interface PipelineCallBackWithResult<K,V> {
    public  void doExecute(Pipeline pipeline,Map<K,Response<V>> map);
}