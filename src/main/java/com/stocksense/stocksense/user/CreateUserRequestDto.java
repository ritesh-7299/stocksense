package com.stocksense.stocksense.user;

import com.stocksense.stocksense.company.Company;

public record CreateUserRequestDto(
        String name,
        String email,
        String password,
        Company company
) {
}
