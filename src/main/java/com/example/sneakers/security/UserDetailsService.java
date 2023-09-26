package com.example.sneakers.security;

import com.example.sneakers.entity.User;
import com.example.sneakers.repository.UserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private final UserRepo userRepo;

  public UserDetailsService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsernameAndIsActiveTrue(username).orElseThrow(
            () -> new UsernameNotFoundException(username + " not found")
    );
    List<SimpleGrantedAuthority> roles = Stream.of(user.getRole())
            .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
  }
}
