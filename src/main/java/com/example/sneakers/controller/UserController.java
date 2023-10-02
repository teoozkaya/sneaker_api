package com.example.sneakers.controller;

import com.example.sneakers.entity.User;
import com.example.sneakers.request.UserRequest;
import com.example.sneakers.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User", description = "Operations related to users")
public class UserController {

  @Autowired
  UserService userService;

  @Operation(summary = "Create user",
          description = "This endpoint allows you to create a new user.")
  @ApiResponse(responseCode = "201", description = "User created")
  @PostMapping("/users")
  public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
  }

  @Operation(summary = "Get all users",
          description = "This endpoint retrieves a list of all users.")
  @ApiResponse(responseCode = "200", description = "All users retrieved")
  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
  }

  @Operation(summary = "Get user by ID",
          description = "This endpoint retrieves the user from their ID.")
  @ApiResponse(responseCode = "200", description = "User found")
  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    return ResponseEntity.ok().body(userService.getUserById(id));
  }

  @Operation(summary = "Get user by username",
          description = "This endpoint retrieves the user from the username.")
  @ApiResponse(responseCode = "200", description = "User found")
  @GetMapping("/users/user/{username}")
  public ResponseEntity<User> getUserById(@PathVariable("username") String username) {
    return ResponseEntity.ok().body(userService.getUserByUsername(username));
  }

  @Operation(summary = "Soft delete all users",
          description = "This endpoint performs a soft delete on all users.")
  @ApiResponse(responseCode = "200", description = "Users deleted successfully")
  @DeleteMapping("/users")
  public ResponseEntity<Object> deleteAll() {
    userService.deleteAllUsers();
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Soft delete a user by their ID",
          description = "This endpoint performs a soft delete on a user by their ID.")
  @ApiResponse(responseCode = "200", description = "User deleted successfully")
  @DeleteMapping("/users/{id}")
  public ResponseEntity<Object> deleteUserById(@PathVariable("id") long id) {
    userService.deleteUserById(id);
    return ResponseEntity.ok().build();
  }
}
