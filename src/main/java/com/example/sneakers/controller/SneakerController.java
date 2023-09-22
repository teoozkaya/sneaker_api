package com.example.sneakers.controller;

import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import com.example.sneakers.request.SneakerRequest;
import com.example.sneakers.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SneakerController {

  @Autowired
  private SneakerService sneakerService;

   @GetMapping("/sneakers")
   public ResponseEntity<List<Sneaker>> getAllSneakers() {
     return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getAll());
   }
  @GetMapping("/sneakers/{brand}")
  public ResponseEntity<List<Sneaker>> getSneakersByBrand(@PathVariable("brand")Brand brand) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getSneakersByBrand(brand));
  }

   @GetMapping("/sneakers/{id}")
   public ResponseEntity<Sneaker> getSneakerById(@PathVariable("id") long id) {
     return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getById(id));
   }

   @PostMapping
   public ResponseEntity<Sneaker> createSneaker(@RequestBody SneakerRequest sneakerRequest) {
     return ResponseEntity.status(HttpStatus.CREATED).body(sneakerService.createSneaker(sneakerRequest));
   }


}
