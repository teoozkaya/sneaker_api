package com.example.sneakers.repository;

import com.example.sneakers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {


}
