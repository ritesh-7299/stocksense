package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.company.Company;

public record AuthUserCreateDto(
        String name,
        String email,
        String password,
        Company company
) {
}
