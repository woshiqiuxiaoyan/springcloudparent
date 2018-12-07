package com.yan.springcloudprovide.redis.util;

public enum LockRetryFrequency {
    VERY_QUICK(10),
    QUICK(50),
    NORMAL(100),
    SLOW(500),
    VERY_SLOW(1000);

    private int retrySpan = 100;

    private LockRetryFrequency(int rf) {
        this.retrySpan = rf;
    }

    public int getRetrySpan() {
        return this.retrySpan;
    }
}