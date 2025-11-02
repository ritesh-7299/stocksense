package com.stocksense.stocksense.common.constant;

public record UserCacheKey(String key) {
    public static UserCacheKey of(String userIdOrEmail) {
        return new UserCacheKey("user:" + userIdOrEmail);
    }
}
