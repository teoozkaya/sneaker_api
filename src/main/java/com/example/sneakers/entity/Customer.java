package com.example.sneakers.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int customerId;
  String name;
  String surname;
  String username;
  String password;

  @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
          CascadeType.PERSIST,
                  CascadeType.MERGE,
                  CascadeType.REMOVE
  })
  @JoinTable(
          name = "customer_ownedSneakers",
          joinColumns = {
                  @JoinColumn(name = "customer-id", referencedColumnName = "customerId")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "sneaker-id", referencedColumnName = "sneakerId")
          }
  )
  Set<Sneaker> ownedSneakers = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER,
          cascade = {
                  CascadeType.PERSIST,
                  CascadeType.MERGE,
                  CascadeType.REMOVE
          })
  @JoinTable(
          name = "customer_sneakerWishlist",
          joinColumns = {
                  @JoinColumn(name = "customer-id", referencedColumnName = "customerId")
          },
          inverseJoinColumns = {
                  @JoinColumn(name = "sneaker-id", referencedColumnName = "sneakerId")
          }
  )
  Set<Sneaker> sneakerWishlist = new HashSet<>();
}
