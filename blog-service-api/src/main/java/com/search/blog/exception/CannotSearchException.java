package com.search.blog.exception;

public class CannotSearchException extends RuntimeException {
    public CannotSearchException(String message, Throwable cause) {
        super(message, cause);
    }
}
