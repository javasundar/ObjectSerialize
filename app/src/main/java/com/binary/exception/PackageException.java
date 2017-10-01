package com.binary.exception;

/**
 * Created by sundarchen on 2017/9/30.
 */

public class PackageException extends Exception {

    public PackageException() {
        super();
    }

    public PackageException(String message) {
        super(message);
    }

    public PackageException(String message, Throwable cause) {
        super(message, cause);
    }

    public PackageException(Throwable cause) {
        super(cause);
    }

    protected PackageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
