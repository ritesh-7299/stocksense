package com.stocksense.stocksense.common.model;

import java.util.UUID;

public record AuthUser(
        String email,
        String name,
        UUID companyId
) {
}
