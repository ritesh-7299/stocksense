package com.stocksense.stocksense.company;

public record CreateCompanyDto(
        String name,
        String address,
        String logo
) {
}
