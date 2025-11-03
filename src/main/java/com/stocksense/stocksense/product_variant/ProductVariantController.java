package com.stocksense.stocksense.product_variant;

import com.stocksense.stocksense.product_variant.dto.CreateProductVariantDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/product/variant")
@RestController
public class ProductVariantController {
    private final ProductVariantService productVariantService;

    @PostMapping()
    String create(@Valid @RequestBody CreateProductVariantDto dto) {
        return productVariantService.create(dto);
    }
}
