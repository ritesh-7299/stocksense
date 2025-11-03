package com.stocksense.stocksense.product_variant;

import com.stocksense.stocksense.common.exceptions.AlreadyPresentException;
import com.stocksense.stocksense.common.exceptions.NotFoundException;
import com.stocksense.stocksense.product.Product;
import com.stocksense.stocksense.product.ProductService;
import com.stocksense.stocksense.product_variant.dto.CreateProductVariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductService productService;

    String create(CreateProductVariantDto dto) {
        Product product = productService.getProductById(UUID.fromString(dto.parentId()))
                .orElseThrow(() -> new NotFoundException("Parent product not found"));

        var variantExists = productVariantRepository.findByCompanyIdAndSku(product.getCompany().getId(), dto.sku());

        if (variantExists.isPresent()) {
            throw new AlreadyPresentException("Sku is already in use");
        }

        ProductVariant variant = new ProductVariant();
        variant.setAttributes(dto.attributes());
        variant.setParent(product);
        variant.setSku(dto.sku());
        productVariantRepository.save(variant);
        return "Created!";
    }
}
