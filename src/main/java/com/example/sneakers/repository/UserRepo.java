package com.example.sneakers.repository;

import com.example.sneakers.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
  Optional<User> findByUsernameAndIsActiveTrue(String username);

}
