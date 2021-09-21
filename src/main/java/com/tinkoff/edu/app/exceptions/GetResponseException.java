package com.tinkoff.edu.app.exceptions;

public class GetResponseException extends RuntimeException {
    public GetResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
