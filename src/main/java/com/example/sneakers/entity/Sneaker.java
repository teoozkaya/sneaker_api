package com.example.sneakers.entity;

import com.example.sneakers.enums.Brand;
import com.example.sneakers.enums.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  Model model;
  String name;
  int releaseYear;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE,
                  CascadeType.REMOVE
          }, mappedBy = "sneakerWishlist")
  Set<Customer> wantingCustomers = new HashSet<>();

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE,
                  CascadeType.REMOVE
          }, mappedBy = "ownedSneakers")
  Set<Customer> ownerCustomers = new HashSet<>();
}
