package com.stocksense.stocksense.product;

import com.stocksense.stocksense.product.dto.CreateProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping
    String create(@Valid @RequestBody CreateProductDto dto) {
        return productService.create(dto);
    }
}
