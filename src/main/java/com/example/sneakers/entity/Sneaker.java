package com.example.sneakers.entity;

import com.example.sneakers.enums.Brand;
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
  String name;
  int releaseYear;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE,
                  CascadeType.REMOVE
          }, mappedBy = "sneakers")
  Set<Customer> customers = new HashSet<>();
}
