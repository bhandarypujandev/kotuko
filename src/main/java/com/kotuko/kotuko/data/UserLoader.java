package com.kotuko.kotuko.data;

import com.kotuko.kotuko.entity.Role;
import com.kotuko.kotuko.entity.User;
import com.kotuko.kotuko.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserLoader implements ApplicationRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(User.builder()
            .email("bhandary@gmail.com")
            .firstName("pujan")
            .lastName("bhandari")
            .role(Role.ADMIN)
            .username("pujan")
            .password(passwordEncoder.encode("pujan"))
            .build());
    }
}
