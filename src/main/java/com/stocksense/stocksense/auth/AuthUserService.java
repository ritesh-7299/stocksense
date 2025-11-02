package com.stocksense.stocksense.auth;

import com.stocksense.stocksense.common.model.AuthUser;
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
}
