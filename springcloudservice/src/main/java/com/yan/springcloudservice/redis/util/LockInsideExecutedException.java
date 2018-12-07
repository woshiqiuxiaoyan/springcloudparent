package com.yan.springcloudservice.redis.util;

public class LockInsideExecutedException extends RuntimeException {
    private static final long serialVersionUID = 2331579241110939344L;

    public LockInsideExecutedException() {
    }

    public LockInsideExecutedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockInsideExecutedException(Throwable cause) {
        super(cause);
    }
}
