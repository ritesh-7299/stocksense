package com.stocksense.stocksense.common.response;

public record ErrorResponse<T>(
        Boolean success,
        String message,
        T data
) {
    public ErrorResponse(String message) {
        this(false, message, null);
    }

    public ErrorResponse(String message, T data) {
        this(false, message, data);
    }
}
