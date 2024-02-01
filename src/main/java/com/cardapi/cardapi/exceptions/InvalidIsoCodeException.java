package com.cardapi.cardapi.exceptions;

public class InvalidIsoCodeException extends IllegalArgumentException {
    public InvalidIsoCodeException(String m) {
        super(m);
    }
}
