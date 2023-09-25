package com.example.sneakers.service;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.repository.CustomerRepo;
import com.example.sneakers.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
  @Autowired
  CustomerRepo customerRepo;

  public List<Customer> getAll() {
    return customerRepo.findAll();
  }

  public Customer getCustomerById(long id) {
    return customerRepo.findById(id).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "customer not found", 404));
  }

  public Customer createCustomer(CustomerRequest customerRequest) {
    return Customer.builder()
            .name(customerRequest.getName())
            .surname(customerRequest.getSurname())
            .username(customerRequest.getUsername())
            .password(customerRequest.getPassword())
            .build();
  }

  public Customer updateCustomerByID(long id, CustomerRequest customerRequest) {
    Customer customer = customerRepo.findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "customer not found", 404));

    customer.setName(customerRequest.getName());
    customer.setSurname(customerRequest.getSurname());
    customer.setUsername(customerRequest.getUsername());
    customer.setPassword(customerRequest.getPassword());
    customerRepo.save(customer);
    return customer;
  }
}
