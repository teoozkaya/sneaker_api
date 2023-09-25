package com.example.sneakers.controller;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.request.CustomerRequest;
import com.example.sneakers.service.CustomerService;
import com.example.sneakers.service.SneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

  @Autowired
  CustomerService customerService;

  @GetMapping("customers")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getAll());
  }

  @GetMapping("customers/{id}")
  public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerById(id));
  }

  @PostMapping("customers")
  public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequest customerRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerRequest));
  }

  @PutMapping("customers/{id}/update")
  public ResponseEntity<Customer> updateCustomerById(@PathVariable("id") long id, @RequestBody CustomerRequest customerRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(customerService.updateCustomerByID(id,customerRequest));
  }
}
