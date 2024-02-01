package com.cardapi.cardapi.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
    public NotFoundException() {}
}