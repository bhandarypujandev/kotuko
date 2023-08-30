package com.kotuko.kotuko.controller;

import com.kotuko.kotuko.dto.LoginReq;
import com.kotuko.kotuko.dto.LoginRes;
import com.kotuko.kotuko.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginReq loginReq){
        System.out.println();
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(loginReq));
    }
}
