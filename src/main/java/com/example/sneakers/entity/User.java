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
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int userId;
  private String username;
  private String password;
  private Role role;
  private boolean isActive;


}
