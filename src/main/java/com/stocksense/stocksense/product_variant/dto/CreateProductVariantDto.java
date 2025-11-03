package com.stocksense.stocksense.product_variant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.UUID;

import java.util.Map;

public record CreateProductVariantDto(
        @NotEmpty(message = "Attributes must not empty")
        Map<String, String> attributes,
        @NotBlank String sku,
        @JsonProperty("parent_id") @NotBlank @UUID String parentId
) {
}
