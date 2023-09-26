package com.example.sneakers.service;

import com.example.sneakers.entity.User;
import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.repository.UserRepo;
import com.example.sneakers.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepo userRepo;

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public User getUserById(long id) {
    return userRepo.findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "User not found", 404));
  }


  public User createUser(UserRequest userRequest) {
    User user = User.builder()
            .name(userRequest.getName())
            .surname(userRequest.getSurname())
            .password(userRequest.getPassword())
            .role(userRequest.getRole())
            .username(userRequest.getUsername())
            .build();
    userRepo.save(user);
    return user;
  }

  public void deleteAllUsers() {
    userRepo.deleteAll();
  }

  public void deleteUserById(long id) {
    userRepo.deleteById(id);
  }
}
