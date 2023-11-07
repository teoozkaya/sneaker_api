package com.example.sneakers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {

  String name;
  String surname;
  String username;
  String password;
  @NotNull(message = " Mail boş olamaz")
  @NotBlank(message = "Mail boş bırakılamaz")
  @Email(message = " Mail e-mail formatında olmalıdır")
  String mail;
}
