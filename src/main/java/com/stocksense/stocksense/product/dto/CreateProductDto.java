package com.stocksense.stocksense.product.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateProductDto(
        @NotBlank String name
) {
}
