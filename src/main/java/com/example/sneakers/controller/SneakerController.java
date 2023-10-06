package com.example.sneakers.controller;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import com.example.sneakers.enums.Model;
import com.example.sneakers.request.CustomerRequest;
import com.example.sneakers.request.SneakerRequest;
import com.example.sneakers.service.SneakerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Sneaker", description = "Operations related to sneaker")
public class SneakerController {

  @Autowired
  private SneakerService sneakerService;


  @Operation(summary = "Get all sneakers",
          description = "This endpoint retrieves a list of all sneakers.")
  @ApiResponse(responseCode = "200", description = "All sneakers retrieved")
  @GetMapping("/sneakers")
  public ResponseEntity<List<Sneaker>> getAllSneakers() {
     return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getAll());
   }

  @Operation(summary = "Get sneaker by id",
          description = "This endpoint retrieves the sneaker from their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker found")
  @GetMapping("/sneakers/{id}")
  public ResponseEntity<Sneaker> getSneakerById(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getById(id));
  }

  @Operation(summary = "Get sneaker by brand",
          description = "This endpoint retrieves the sneaker from the brand.")
  @ApiResponse(responseCode = "200", description = "Brand found and sneakers are retrieved")
  @GetMapping("/sneakers/brand/{brand}")
  public ResponseEntity<List<Sneaker>> getSneakersByBrand(@PathVariable("brand")Brand brand) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getSneakersByBrand(brand));
  }

  @Operation(summary = "Get sneaker by model",
          description = "This endpoint retrieves the sneaker from the model.")
  @ApiResponse(responseCode = "200", description = "Model found and sneakers are retrieved")
  @GetMapping("/sneakers/model/{model}")
  public ResponseEntity<List<Sneaker>> getSneakersByModel(@PathVariable("model") Model model) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getSneakersByModel(model));
  }


  @Operation(summary = "Get all sneakers by release year descending",
          description = "This endpoint retrieves the sneakers by release year descending.")
  @ApiResponse(responseCode = "200", description = "Sneakers are retrieved")
  @GetMapping("/sneakers/getAll/releaseYear/desc")
  public ResponseEntity<List<Sneaker>> getSneakersByReleaseYearDesc() {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.getSneakersByRelaseYearDesc());
  }
  @Operation(summary = "Create sneaker",
          description = "This endpoint allows you to create a new sneaker.")
  @ApiResponse(responseCode = "201", description = "Sneaker created")
   @PostMapping("/sneakers/create")
   public ResponseEntity<Sneaker> createSneaker(@RequestBody SneakerRequest sneakerRequest) {
     return ResponseEntity.status(HttpStatus.CREATED).body(sneakerService.createSneaker(sneakerRequest));
   }

  @Operation(summary = "Update sneaker by ID",
          description = "This endpoint allows you to update a sneaker's information by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker updated successfully")
  @PutMapping("/sneakers/{id}/update")
  public ResponseEntity<Sneaker> updateSneakerById(@PathVariable("id") long id, @RequestBody SneakerRequest sneakerRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.updateSneakerByID(id,sneakerRequest));
  }

  @Operation(summary = "Soft delete all sneakers",
          description = "This endpoint performs a soft delete on all sneakers.")
  @ApiResponse(responseCode = "200", description = "Sneakers deleted successfully")
  @DeleteMapping("/sneakers/delete")
  public ResponseEntity<Object> deleteAll() {
    sneakerService.deleteAllSneakers();
     return ResponseEntity.ok().build();
  }

  @Operation(summary = "Soft delete a sneaker by their ID",
          description = "This endpoint performs a soft delete on a sneaker by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker deleted successfully")
  @DeleteMapping("/sneakers/delete/{id}")
  public ResponseEntity<Object> deleteSneakerById(@PathVariable("id") long id) {
    sneakerService.deleteSneakerById(id);
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Search sneaker",
          description = "This endpoint searches sneaker.")
  @ApiResponse(responseCode = "200", description = "Sneaker found")
  @GetMapping("/sneakers/search/{name}")
  public ResponseEntity<List<Sneaker>> searchSneaker(@PathVariable("name") String name) {
    return ResponseEntity.status(HttpStatus.OK).body(sneakerService.search(name));
  }
}
