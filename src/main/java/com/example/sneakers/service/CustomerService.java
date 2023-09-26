package com.example.sneakers.service;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.repository.CustomerRepo;
import com.example.sneakers.repository.SneakerRepo;
import com.example.sneakers.request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
  @Autowired
  CustomerRepo customerRepo;

  @Autowired
  SneakerService sneakerService;

  public List<Customer> getAll() {
    return customerRepo.findAll();
  }

  public Customer getCustomerById(long id) {
    return customerRepo.findById(id).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "customer not found", 404));
  }

  public Customer createCustomer(CustomerRequest customerRequest) {
    Customer customer = Customer.builder()
            .name(customerRequest.getName())
            .surname(customerRequest.getSurname())
            .username(customerRequest.getUsername())
            .password(customerRequest.getPassword())
            .build();
    customerRepo.save(customer);
    return customer;
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

  public Customer addSneakerToCustomer(long customerId, long sneakerId) {
    Customer customer = customerRepo.findById(customerId)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "customer not found", 404));

    Sneaker sneaker = sneakerService.getById(sneakerId);

    customer.getSneakers().add(sneaker);
    customerRepo.save(customer);
    return customer;
  }

  public Set<Sneaker> getSneakersOfCustomer(long id) {
    Customer customer = customerRepo.findById(id).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "customer not found", 404));

    return customer.getSneakers();
  }

  public void deleteAllCustomers() {
    customerRepo.deleteAll();
  }

  public void deleteCustomerById(long id) {
    customerRepo.deleteById(id);
  }
}
