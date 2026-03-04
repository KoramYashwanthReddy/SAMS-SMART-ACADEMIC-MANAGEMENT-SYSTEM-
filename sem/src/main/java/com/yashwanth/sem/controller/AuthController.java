package com.yashwanth.sem.controller;

import com.yashwanth.sem.dto.LoginRequest;
import com.yashwanth.sem.dto.LoginResponse;
import com.yashwanth.sem.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}