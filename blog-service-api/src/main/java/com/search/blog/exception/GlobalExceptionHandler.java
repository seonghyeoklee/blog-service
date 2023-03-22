package com.search.blog.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CannotSearchException.class)
    protected ResponseEntity<?> handleException(CannotSearchException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
