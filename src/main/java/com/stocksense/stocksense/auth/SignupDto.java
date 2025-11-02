package com.stocksense.stocksense.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record SignupDto(
        @NotBlank String email,
        @NotBlank String name,
        @NotBlank String password,
        @JsonProperty("company_name") @NotBlank String companyName,
        @JsonProperty("company_logo") String companyLogo,
        @NotBlank String address
) {
}
