package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.common.model.AuthUser;
import com.stocksense.stocksense.common.model.AuthUserWithPassword;
import com.stocksense.stocksense.user.CreateUserRequestDto;
import com.stocksense.stocksense.user.User;
import com.stocksense.stocksense.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthUserService {
    private final UserService userService;

    AuthUser getAuthUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    AuthUserWithPassword getAuthUserByEmailWithPassword(String email) {
        return userService.getUserByEmailWithPassword(email);
    }

    AuthUser createAuthUser(AuthUserCreateDto dto) {
        User user = userService.createUser(
                new CreateUserRequestDto(
                        dto.name(),
                        dto.email(),
                        dto.password(),
                        dto.company()
                )
        );

        return new AuthUser(user.getEmail(), user.getName(), user.getCompany().getId());
    }

}
