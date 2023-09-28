package com.example.sneakers.config;

import com.example.sneakers.security.JwtAccessDeniedHandler;
import com.example.sneakers.security.JwtAuthenticationEntryPoint;
import com.example.sneakers.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

  private final JwtFilter jwtFilter;
  private final JwtAccessDeniedHandler accessDeniedHandler;
  private final JwtAuthenticationEntryPoint authenticationEntryPoint;

  public SecurityConfig(@Lazy JwtFilter jwtFilter, JwtAccessDeniedHandler accessDeniedHandler, JwtAuthenticationEntryPoint authenticationEntryPoint) {
    this.jwtFilter = jwtFilter;
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
    return security
            .csrf(AbstractHttpConfigurer::disable)
            .cors()
            .and()
            .authorizeHttpRequests(auth -> auth
                    //.requestMatchers("/api/users").hasAnyAuthority("ROLE_SPECTATOR", "ROLE_ADMIN")
                    .requestMatchers("/api/auth/login",
                            "/api/sneakers",
                            "/api/sneakers/brand",
                            "/api/customers/create").permitAll()
                    .anyRequest().authenticated()
            )/*
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/users").hasAnyAuthority("ROLE_CUSTOMER_OWNER", "ROLE_ADMIN")
                    .requestMatchers("/api/auth/login",
                            "/api/auth/**",
                            "/v3/api-docs.yaml",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll()
                    .anyRequest().authenticated()
                    */
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }
}
