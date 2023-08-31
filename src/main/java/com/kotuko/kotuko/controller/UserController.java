package com.kotuko.kotuko.controller;

import com.kotuko.kotuko.dto.PageableResponse;
import com.kotuko.kotuko.dto.UserProjection;
import com.kotuko.kotuko.dto.UserReq;
import com.kotuko.kotuko.dto.UserResponse;
import com.kotuko.kotuko.service.AuthenticationService;
import com.kotuko.kotuko.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Get users by user name", description = "Returns the user of given username")
    @GetMapping("/{username}")
    public ResponseEntity<UserProjection> getUsersByUserName(@PathVariable String username) {
        Optional<UserProjection> userByUserName = userService.getUserByUserName(username);
        if (userByUserName.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(userByUserName.get());
        else
            return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get users by email", description = "Returns the user of given email")
    @GetMapping("email/{email}")
    public ResponseEntity<UserProjection> getUsersByEmail(@PathVariable String email) {
        Optional<UserProjection> userByEmail = userService.getUserByEmail(email);
        if (userByEmail.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(userByEmail.get());
        else
            return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Get users by first name", description = "Returns the list of users whose first name matches")
    @GetMapping("firstName/{firstName}")
    public ResponseEntity<PageableResponse<UserProjection>> getUsersByFirstName(@PathVariable String firstName, @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(new PageableResponse<>(userService.getUserByFirstName(firstName, page)));
    }

    @Operation(summary = "Get users by last name", description = "Returns the list of users whose last name matches")
    @GetMapping("last-name/{lastName}")
    public ResponseEntity<PageableResponse<UserProjection>> getUsersByLastName(@PathVariable String lastName,@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.status(HttpStatus.OK).body(new PageableResponse<>(userService.getUserByLastName(lastName, page)));
    }


    @Operation(summary = "Create new user", description = "Creates new user")
    @PostMapping()
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserReq user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.createUser(user));
    }

    @Operation(summary = "Edit existing user", description = "Edits existing user")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> editUser(@PathVariable int id,@Valid @RequestBody UserReq user) {
        return userService.editUser(id,user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete existing user", description = "Delete existing user")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }



}
