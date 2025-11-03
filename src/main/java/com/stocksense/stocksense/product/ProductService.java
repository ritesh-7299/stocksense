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

        Optional<Product> existingProduct = productRepository.findByCompanyIdAndName(company.getId(), dto.name());
        if (existingProduct.isPresent()) {
            throw new AlreadyPresentException("Product with same name is already present");
        }
        Product product = new Product();
        product.setCompany(company);
        product.setName(dto.name());
        productRepository.save(product);
        return "Saved!";
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }
}
