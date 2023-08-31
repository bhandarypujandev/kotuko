package com.kotuko.kotuko.service;

import com.kotuko.kotuko.dto.LoginReq;
import com.kotuko.kotuko.dto.LoginRes;
import com.kotuko.kotuko.dto.UserReq;
import com.kotuko.kotuko.dto.UserResponse;

public interface AuthenticationService {

    LoginRes login(LoginReq loginReq);
    UserResponse createUser(UserReq user);

}
