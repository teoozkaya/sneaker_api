package com.example.sneakers.controller;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import com.example.sneakers.request.CustomerRequest;
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

  @GetMapping("/hello")
  public String hello() {
    return "Juice Wrld";
  }

  @GetMapping("/sneakers")
   public ResponseEntity<List<Sneaker>> getAllSneakers() {
     return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getAll());
   }
  @GetMapping("/sneakers/{id}")
  public ResponseEntity<Sneaker> getSneakerById(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getById(id));
  }

  @GetMapping("/sneakers/brand/{brand}")
  public ResponseEntity<List<Sneaker>> getSneakersByBrand(@PathVariable("brand")Brand brand) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getSneakersByBrand(brand));
  }

   @PostMapping("/sneakers")
   public ResponseEntity<Sneaker> createSneaker(@RequestBody SneakerRequest sneakerRequest) {
     return ResponseEntity.status(HttpStatus.CREATED).body(sneakerService.createSneaker(sneakerRequest));
   }

  @PutMapping("/sneakers/{id}/update")
  public ResponseEntity<Sneaker> updateSneakerById(@PathVariable("id") long id, @RequestBody SneakerRequest sneakerRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.updateSneakerByID(id,sneakerRequest));
  }

  @DeleteMapping("/sneakers")
  public ResponseEntity<Object> deleteAll() {
    sneakerService.deleteAllSneakers();
     return ResponseEntity.ok().build();
  }

  @DeleteMapping("/sneakers/{id}")
  public ResponseEntity<Object> deleteSneakerById(@PathVariable("id") long id) {
    sneakerService.deleteSneakerById(id);
    return ResponseEntity.ok().build();
  }
}
