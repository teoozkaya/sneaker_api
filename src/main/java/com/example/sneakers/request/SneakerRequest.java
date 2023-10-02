package com.example.sneakers.request;

import com.example.sneakers.enums.Brand;
import com.example.sneakers.enums.Model;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.awt.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SneakerRequest {

  @NotNull(message = "sneaker name con not be left as null, please enter a name for the sneaker")
  String name;
  Brand brand;
  Model model;
  int releaseYear;

}
