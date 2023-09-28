package com.example.sneakers.controller;

import com.example.sneakers.request.LoginRequest;
import com.example.sneakers.security.Token;
import com.example.sneakers.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


  @Autowired
  AuthService authService;

  @PostMapping("/login")
  public Token login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}