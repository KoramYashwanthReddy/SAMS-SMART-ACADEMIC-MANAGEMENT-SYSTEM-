package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.LoginRequest;
import com.yashwanth.sem.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);
}