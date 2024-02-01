package com.cardapi.cardapi.exceptions;

public class InvalidCardCostException extends IllegalArgumentException {
    public InvalidCardCostException(String m) {
        super(m);
    }
}
