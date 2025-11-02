package com.stocksense.stocksense.common.model;

public record AuthUserWithPassword(
        String email,
        String password
) {
}
