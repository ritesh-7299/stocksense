package com.stocksense.stocksense.user;

import com.stocksense.stocksense.common.exceptions.AlreadyPresentException;
import com.stocksense.stocksense.common.exceptions.NotFoundException;
import com.stocksense.stocksense.common.model.AuthUser;
import com.stocksense.stocksense.common.model.AuthUserWithPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(CreateUserRequestDto dto) {
        var eUser = userRepository.findByEmail(
                dto.email()
        );
        if (eUser.isPresent()) {
            throw new AlreadyPresentException("Email is already in use");
        }
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
        return new AuthUser(user.get().getEmail(), user.get().getName(), user.get().getCompany().getId());
    }

    public AuthUserWithPassword getUserByEmailWithPassword(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return new AuthUserWithPassword(user.get().getEmail(), user.get().getPassword());
    }
}
