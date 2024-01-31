package com.cardapi.cardapi.helpers;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
    public NotFoundException() {}
}