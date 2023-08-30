package com.kotuko.kotuko.service;

import com.kotuko.kotuko.dto.LoginReq;
import com.kotuko.kotuko.dto.LoginRes;

public interface AuthenticationService {

    LoginRes login(LoginReq loginReq);
}
