package com.example.sneakers.security;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenUtils {

  @Value("${jwt-variables.KEY}")
  private String key;
  @Value("${jwt-variables.ISSUER}")
  private String issuer;
  @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
  private long tokenExpireMinute;

  public String generate(Authentication authentication) {
    String username = ((UserDetails) authentication.getPrincipal()).getUsername();
    return JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpireMinute * 60 * 1000))
            .withIssuer(issuer)
            .sign(Algorithm.HMAC256(key.getBytes()));
  }

  public DecodedJWT verifyJWT(String token) {
    Algorithm algorithm = Algorithm.HMAC256(key.getBytes());
    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
    try {
      return jwtVerifier.verify(token);
    } catch (RuntimeException ex) {
      log.error(ex.getMessage());
      throw ex;
    }
  }
}


