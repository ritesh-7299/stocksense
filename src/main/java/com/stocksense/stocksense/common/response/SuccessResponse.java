package com.stocksense.stocksense.common.response;

public record SuccessResponse<T>(
        Boolean success,
        String message,
        T data
) {
    public SuccessResponse(String message) {
        this(true, message, null);
    }

    public SuccessResponse(String message, T data) {
        this(true, message, data);
    }
}
