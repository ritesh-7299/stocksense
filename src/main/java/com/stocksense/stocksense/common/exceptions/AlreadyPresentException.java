package com.stocksense.stocksense.common.exceptions;

public class AlreadyPresentException extends RuntimeException {
    public AlreadyPresentException(String message) {
        super(message);
    }
}
