package com.example.sneakers.entity;

import com.example.sneakers.enums.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int customerId;
  String name;
  String surname;
  String username;
  String password;
  /*
  @OneToMany(
          mappedBy = "customer",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  Sneaker sneaker;
   */

}
