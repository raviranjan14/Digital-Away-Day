package com.deloitte.digitalawayday.exception;

public class BaseException extends Exception {

    private static final long serialVersionUID = 1L;

    public BaseException(String message, Throwable parent) {
        super(message, parent);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable parent) {
        super(parent);
    }

}
