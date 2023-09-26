package com.example.sneakers.security;

import lombok.*;
import org.springframework.context.annotation.Lazy;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
  private String token;
}
