package com.stocksense.stocksense.product;

import com.stocksense.stocksense.common.exceptions.AlreadyPresentException;
import com.stocksense.stocksense.common.exceptions.NotFoundException;
import com.stocksense.stocksense.common.model.AuthUser;
import com.stocksense.stocksense.common.utils.AuthUtilsService;
import com.stocksense.stocksense.company.Company;
import com.stocksense.stocksense.company.CompanyService;
import com.stocksense.stocksense.product.dto.CreateProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CompanyService companyService;
    private final AuthUtilsService authUtilsService;

    String create(CreateProductDto dto) {
        AuthUser authUser = authUtilsService.getCurrentUser();

        Company company = companyService.getById(authUser.companyId())
                .orElseThrow(() -> new NotFoundException("Company not found"));

        Optional<Product> existingProduct = productRepository.findByCompanyIdAndSku(company.getId(), dto.sku());
        if (existingProduct.isPresent()) {
            throw new AlreadyPresentException("Product with same SKU is already present");
        }
        Product product = new Product();
        product.setSku(dto.sku());
        product.setCompany(company);
        product.setName(dto.name());
        product.setQuantity(dto.quantity());
        product.setPrice(dto.price());
        productRepository.save(product);
        return "Saved!";
    }
}
