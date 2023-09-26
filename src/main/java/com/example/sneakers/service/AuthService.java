package com.example.sneakers.service;

import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.request.LoginRequest;
import com.example.sneakers.security.Token;
import com.example.sneakers.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  TokenUtils tokenUtils;

  @Autowired
  AuthenticationManager authManager;


  public Token login(LoginRequest loginRequest) {
    try {
      Authentication auth = authManager
              .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
      return Token.builder()
              .token(tokenUtils.generate(auth))
              .build();

    } catch (Exception e) {
      throw new GenericException(HttpStatus.NOT_FOUND, loginRequest.getUsername() + " username not found", 404);
    }
  }
}
