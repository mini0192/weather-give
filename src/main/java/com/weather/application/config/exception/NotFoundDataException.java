package com.weather.application.config.exception;

public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException(String message) { super(message); }
}
