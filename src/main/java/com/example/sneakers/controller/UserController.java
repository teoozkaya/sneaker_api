package com.example.sneakers.controller;

import com.example.sneakers.entity.User;
import com.example.sneakers.request.UserRequest;
import com.example.sneakers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    return ResponseEntity.ok().body(userService.getUserById(id));
  }

  @GetMapping("/users/user/{username}")
  public ResponseEntity<User> getUSerById(@PathVariable("username") String username) {
    return ResponseEntity.ok().body(userService.getUserByUsername(username));
  }

  @DeleteMapping("/users")
  public ResponseEntity<Object> deleteAll() {
    userService.deleteAllUsers();
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Object> deleteSneakerById(@PathVariable("id") long id) {
    userService.deleteUserById(id);
    return ResponseEntity.ok().build();
  }
}
