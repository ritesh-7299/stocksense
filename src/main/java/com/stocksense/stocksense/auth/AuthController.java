package com.stocksense.stocksense.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    TokenResponseDto signup(@Valid @RequestBody SignupDto dto) {
        return authService.signup(dto);
    }

    @PostMapping("/login")
    TokenResponseDto login(@Valid @RequestBody LoginDto dto) {
        return authService.login(dto);
    }

    @GetMapping("/test")
    String test() {
        return "Test done";
    }
}
