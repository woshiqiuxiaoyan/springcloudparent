package com.yan.springcloudservice.redis.impl;

import redis.clients.jedis.Pipeline;

public interface PipelineCallBackWithoutResult {

    public  void doExecute(Pipeline pipeline);
}