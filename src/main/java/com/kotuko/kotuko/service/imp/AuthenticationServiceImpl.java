package com.kotuko.kotuko.service.imp;

import com.kotuko.kotuko.dto.LoginReq;
import com.kotuko.kotuko.dto.LoginRes;
import com.kotuko.kotuko.dto.UserReq;
import com.kotuko.kotuko.dto.UserResponse;
import com.kotuko.kotuko.entity.Role;
import com.kotuko.kotuko.entity.User;
import com.kotuko.kotuko.respository.UserRepository;
import com.kotuko.kotuko.service.AuthenticationService;
import com.kotuko.kotuko.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public LoginRes login(LoginReq loginReq) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        var user = userRepository.findByUsername(loginReq.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return LoginRes.builder().token(jwt).build();
    }

    @Override
    public UserResponse createUser(UserReq user) {
        User savedUser = userRepository.save(User.builder()
            .dateOfBirth(user.getDateOfBirth())
            .username(user.getUsername())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .role(Role.USER)
            .build());

        return new UserResponse(savedUser);
    }
}
