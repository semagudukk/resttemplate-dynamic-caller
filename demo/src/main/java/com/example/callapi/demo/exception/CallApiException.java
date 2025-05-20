package com.example.callapi.demo.exception;


public class CallApiException extends RuntimeException {
    public CallApiException(String message, Throwable cause) {
        super(message, cause);
    }
}