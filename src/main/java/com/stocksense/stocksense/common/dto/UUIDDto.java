package com.stocksense.stocksense.common.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UUID;

public record UUIDDto(
        @NotBlank @UUID String id
) {
}
