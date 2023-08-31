package com.kotuko.kotuko.service;

import com.kotuko.kotuko.dto.UserProjection;
import com.kotuko.kotuko.dto.UserReq;
import com.kotuko.kotuko.dto.UserResponse;
import com.kotuko.kotuko.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    UserDetailsService userDetailsService();
    Optional<UserProjection> getUserByUserName(String username);
    Optional<UserProjection> getUserByEmail(String email);

    Page<UserProjection> getUserByFirstName(String firstName,int pageNumber);
    Page<UserProjection> getUserByLastName(String firstName,int pageNumber);

    Optional<User> getUserById(int id);

    ResponseEntity<UserResponse> editUser(int id, UserReq editRequest);

    ResponseEntity<String> deleteUser(int id);


}
