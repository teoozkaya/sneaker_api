package com.example.sneakers.entity;

import com.example.sneakers.enums.Brand;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.awt.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sneaker")
public class Sneaker {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int sneakerId;
  Brand brand;
  String name;
  int releaseYear;
  /*
  @JoinColumn(name = "customer")
  Customer customer;
  */


}
