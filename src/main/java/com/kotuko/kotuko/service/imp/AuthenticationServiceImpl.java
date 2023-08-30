package com.kotuko.kotuko.service.imp;

import com.kotuko.kotuko.dto.LoginReq;
import com.kotuko.kotuko.dto.LoginRes;
import com.kotuko.kotuko.respository.UserRepository;
import com.kotuko.kotuko.service.AuthenticationService;
import com.kotuko.kotuko.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    @Override
    public LoginRes login(LoginReq loginReq) {
        System.out.println(loginReq.toString());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        var user = userRepository.findByUsername(loginReq.getUsername())
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return LoginRes.builder().token(jwt).build();
    }
}
