package com.greenittracker.exception;

public class InvalidResourceStateException extends RuntimeException {

    public InvalidResourceStateException(String message) {
        super(message);
    }
}