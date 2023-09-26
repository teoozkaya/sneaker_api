package com.example.sneakers.request;

import com.example.sneakers.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {


  String name;
  String surname;

  @NotNull(message = "username can not be left as null")
  String username;
  @NotNull(message = "password can not be left as null")
  String password;
  @NotNull(message = "role can not be left as null")
  Role role;
}
