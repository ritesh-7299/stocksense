package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.company.Company;
import com.stocksense.stocksense.company.CompanyService;
import com.stocksense.stocksense.company.CreateCompanyDto;
import com.stocksense.stocksense.user.CreateUserRequestDto;
import com.stocksense.stocksense.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserService userService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    SignupResponseDto signup(SignupDto dto) {
        System.out.println(dto);
        Company company = companyService.createCompany(
                new CreateCompanyDto(dto.companyName(), dto.address(), dto.companyLogo())
        );

        userService.createUser(
                new CreateUserRequestDto(
                        dto.name(),
                        dto.email(),
                        passwordEncoder.encode(dto.password()),
                        company
                )
        );
        return new SignupResponseDto(jwtUtil.generateToken(dto.email()));
    }
}
