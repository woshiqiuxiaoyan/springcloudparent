package com.yan.springcloudprovide.redis.util;

public class LockCantObtainException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LockCantObtainException() {
    }

    public LockCantObtainException(String message) {
        super(message);
    }
}
