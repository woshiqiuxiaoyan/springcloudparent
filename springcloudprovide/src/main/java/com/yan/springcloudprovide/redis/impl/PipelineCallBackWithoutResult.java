package com.yan.springcloudprovide.redis.impl;

import redis.clients.jedis.Pipeline;

public interface PipelineCallBackWithoutResult {

    public  void doExecute(Pipeline pipeline);
}