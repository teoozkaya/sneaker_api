package com.example.sneakers.service;

import com.example.sneakers.entity.User;
import com.example.sneakers.enums.Role;
import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.repository.UserRepo;
import com.example.sneakers.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired
  UserRepo userRepo;

  public User createUser(UserRequest userRequest) {
    User user = User.builder()
            .username(userRequest.getUsername())
            .password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
            .role(userRequest.getRole())
            .isActive(true)
            .build();
    checkUsername(user.getUsername());
    userRepo.save(user);
    return user;
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User getUserById(long id) {
    return userRepo.findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "User not found", 404));
  }


  public void deleteAllUsers() {
    userRepo.deleteAll();
  }

  public void deleteUserById(long id) {
    userRepo.deleteById(id);
  }

  public void checkUsername(String username) {
    Optional<User> user = userRepo.findByUsernameAndIsActiveTrue(username);
    if (user.isPresent()) {
      throw new GenericException(HttpStatus.BAD_REQUEST,
              username + " Sorry, this username is already taken, please choose a new username.", 400);
    }
  }

  public User getUserByUsername(String username) {
    return userRepo.findByUsernameAndIsActiveTrue(username)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "User not found", 404));
  }
}
