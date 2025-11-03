package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.common.exceptions.UnAuthorizedException;
import com.stocksense.stocksense.common.model.AuthUserWithPassword;
import com.stocksense.stocksense.company.Company;
import com.stocksense.stocksense.company.CompanyService;
import com.stocksense.stocksense.company.CreateCompanyDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthUserService authUserService;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    TokenResponseDto signup(SignupDto dto) {
        Company company = companyService.createCompany(
                new CreateCompanyDto(dto.companyName(), dto.address(), dto.companyLogo())
        );

        authUserService.createAuthUser(
                new AuthUserCreateDto(dto.name(), dto.email(), passwordEncoder.encode(dto.password()), company)
        );

        return new TokenResponseDto(jwtUtil.generateToken(dto.email()));
    }

    TokenResponseDto login(LoginDto dto) {
        AuthUserWithPassword user = authUserService.getAuthUserByEmailWithPassword(dto.email());
        System.out.println("USer" + user);
        if (user == null || !passwordEncoder.matches(dto.password(), user.password())) {
            throw new UnAuthorizedException("Invalid Credentials");
        }
        return new TokenResponseDto(jwtUtil.generateToken(dto.email()));
    }

}
