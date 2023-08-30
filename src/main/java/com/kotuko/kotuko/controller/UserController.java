package com.kotuko.kotuko.controller;

import com.kotuko.kotuko.dto.PageableResponse;
import com.kotuko.kotuko.dto.UserProjection;
import com.kotuko.kotuko.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get users by first name", description = "Returns the list of users whose first name matches")
    @GetMapping("firstName/{firstName}")
    public ResponseEntity<PageableResponse<UserProjection>> getUsersByFirstName(@PathVariable  String firstName){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }





}
