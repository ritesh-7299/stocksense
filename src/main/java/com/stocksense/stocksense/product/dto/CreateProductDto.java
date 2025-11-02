package com.stocksense.stocksense.product.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProductDto(
        @NotBlank String sku,
        @NotBlank String name,
        int quantity,
        int price
) {
}
