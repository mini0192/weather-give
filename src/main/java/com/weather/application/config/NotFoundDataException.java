package com.weather.application.config;

public class NotFoundDataException extends RuntimeException {
    public NotFoundDataException(String message) { super(message); }
}
