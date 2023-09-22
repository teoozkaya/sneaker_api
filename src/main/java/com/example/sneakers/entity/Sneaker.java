package com.example.sneakers.entity;

import com.example.sneakers.enums.Brand;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.awt.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Sneaker {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int sneakerId;
  Brand brand;
  String name;
  int releaseYear;
}
