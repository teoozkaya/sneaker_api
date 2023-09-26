package com.example.sneakers.entity;

import com.example.sneakers.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  String name;
  String surname;
  String username;
  String password;
  Role role;
  boolean isActive;


}
