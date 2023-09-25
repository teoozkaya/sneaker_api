package com.example.sneakers.request;

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
}
