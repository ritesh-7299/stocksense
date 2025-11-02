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
    SignupResponseDto signup(@Valid @RequestBody SignupDto dto) {
        return authService.signup(dto);
    }

    @GetMapping("/test")
    String test() {
        return "Test done";
    }
}
