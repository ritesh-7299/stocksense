package com.stocksense.stocksense.user;

import com.stocksense.stocksense.common.model.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(CreateUserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setName(dto.name());
        user.setCompany(dto.company());
        user.setPassword(dto.password());
        return userRepository.save(user);
    }

    public AuthUser getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new RuntimeException();
        }
        return new AuthUser(user.get().getEmail(), user.get().getName());
    }
}
