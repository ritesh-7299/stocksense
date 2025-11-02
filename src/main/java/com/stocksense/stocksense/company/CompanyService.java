package com.stocksense.stocksense.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(CreateCompanyDto dto) {
        Company company = new Company();
        company.setName(dto.name());
        company.setAddress(dto.address());
        company.setLogo(dto.logo());
        return companyRepository.save(company);
    }

    public Optional<Company> getById(UUID id) {
        return companyRepository.findById(id);
    }
}
