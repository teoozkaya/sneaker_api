package com.example.sneakers.controller;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.request.CustomerRequest;
import com.example.sneakers.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Tag(name = "Customer", description = "Operations related to customer")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @Operation(summary = "Get all Customers",
          description = "This endpoint retrieves a list of all customers.")
  @ApiResponse(responseCode = "200", description = "All customers retrieved")
  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
  }

  @Operation(summary = "Get customer by id",
          description = "This endpoint retrieves the customer from their customer ID.")
  @ApiResponse(responseCode = "200", description = "Customer found")
  @GetMapping("/customers/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
  }

  @Operation(summary = "Create customer",
          description = "This endpoint allows you to create a new customer .")
  @ApiResponse(responseCode = "201", description = "Customer created")
  @PostMapping("/customers/create")
  public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
  }

  @Operation(summary = "Update customer by ID",
          description = "This endpoint allows you to update a customer's information by their ID.")
  @ApiResponse(responseCode = "200", description = "Customer updated successfully")
  @PutMapping("/customers/{id}/update")
  public ResponseEntity<Customer> updateCustomerById(@Valid @PathVariable("id") long id, @RequestBody CustomerRequest customerRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerByID(id, customerRequest));
  }

  @Operation(summary = "Add sneaker to the customer owned list by ID",
          description = "This endpoint allows you to add a sneaker to a customer's owned sneakers by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker added to the customer successfully")
  @PutMapping("/customers/{customerId}/addOwnedSneaker/{sneakerID}")
  public ResponseEntity<Customer> addSneakerToCustomerOwnedList(@PathVariable("customerId") long customerId, @PathVariable("sneakerID") long sneakerId) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.addSneakerToOwnedList(customerId, sneakerId));
  }

  @Operation(summary = "Delete sneaker from the customer owned list by ID",
          description = "This endpoint allows you to delete a sneaker from a customer's owned sneakers by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker deleted from the customer successfully")
  @PutMapping("/customers/{customerId}/deleteOwnedSneaker/{sneakerID}")
  public ResponseEntity<Customer> deleteSneakerFromCustomerOwnedList(@PathVariable("customerId") long customerId, @PathVariable("sneakerID") long sneakerId) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteSneakerFromOwnedList(customerId, sneakerId));
  }
  @Operation(summary = "Get sneakers of a customer by ID",
          description = "This endpoint allows you to get a customer's owned sneakers by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker list retrieved successfully")
  @GetMapping("/customers/{id}/ownedSneakers")
  public ResponseEntity<Set<Sneaker>> getSneakersOfCustomerOwnedList(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getOwnedSneakersOfCustomer(id));
  }

  @Operation(summary = "Add sneaker to the customer by ID",
          description = "This endpoint allows you to add a sneaker to a customer's owned sneakers by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker added to the customer successfully")
  @PutMapping("/customers/{customerId}/addWantedSneaker/{sneakerID}")
  public ResponseEntity<Customer> addSneakerToCustomerWishList(@PathVariable("customerId") long customerId, @PathVariable("sneakerID") long sneakerId) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.addSneakerToWishList(customerId, sneakerId));
  }

  @Operation(summary = "Get sneakers of a customer by ID",
          description = "This endpoint allows you to get a customer's owned sneakers by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker list retrieved successfully")
  @GetMapping("/customers/{id}/wantedSneakers")
  public ResponseEntity<Set<Sneaker>> getWantedSneakersOfCustomer(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getWantedSneakersOfCustomer(id));
  }

  @Operation(summary = "Delete sneaker from the customer wishlist by ID",
          description = "This endpoint allows you to delete a sneaker from a customer's wishlist by their ID.")
  @ApiResponse(responseCode = "200", description = "Sneaker deleted from the customer successfully")
  @PutMapping("/customers/{customerId}/deleteWishedSneaker/{sneakerID}")
  public ResponseEntity<Customer> deleteSneakerFromCustomerWishlist(@PathVariable("customerId") long customerId, @PathVariable("sneakerID") long sneakerId) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.deleteSneakerFromWishlist(customerId, sneakerId));
  }

  @Operation(summary = "Soft delete all customers",
          description = "This endpoint performs a soft delete on all customers.")
  @ApiResponse(responseCode = "200", description = "Customers deleted successfully")
  @DeleteMapping("/customers")
  public ResponseEntity<Object> deleteAll() {
    customerService.deleteAllCustomers();
    return ResponseEntity.ok().build();
  }

  @Operation(summary = "Soft delete customer by ID",
          description = "This endpoint performs a soft delete on a customer by their ID.")
  @ApiResponse(responseCode = "200", description = "Customer deleted successfully")
  @DeleteMapping("/customers/{id}")
  public ResponseEntity<Object> deleteCustomerById(@PathVariable("id") long id) {
    customerService.deleteCustomerById(id);
    return ResponseEntity.ok().build();
  }
}
