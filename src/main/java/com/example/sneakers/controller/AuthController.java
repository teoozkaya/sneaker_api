package com.example.sneakers.controller;

import com.example.sneakers.request.LoginRequest;
import com.example.sneakers.security.Token;
import com.example.sneakers.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Operations related to authentication")
public class AuthController {


  @Autowired
  AuthService authService;

  @PostMapping("/login")
  @Operation(summary = "Login",
          description = "This endpoint allows a user to log in and returns a token.")
  @ApiResponse(responseCode = "200", description = "Login successful")
  public Token login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }
}
