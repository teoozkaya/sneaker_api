package com.example.sneakers.service;

import com.example.sneakers.entity.Customer;
import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import com.example.sneakers.exceptions.GenericException;
import com.example.sneakers.repository.SneakerRepo;
import com.example.sneakers.request.SneakerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SneakerService {

  @Autowired
  private SneakerRepo sneakerRepo;

  public List<Sneaker> getAll() {
    return sneakerRepo.findAll();
  }

  public Sneaker getById(long id) {
    return sneakerRepo.findById(id).
            orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "sneaker not found", 404));
  }

  public Sneaker createSneaker(SneakerRequest sneakerRequest) {
    Sneaker sneaker = Sneaker.builder().
            brand(sneakerRequest.getBrand()).
            name(sneakerRequest.getName()).
            releaseYear(sneakerRequest.getReleaseYear()).
            build();
    sneakerRepo.save(sneaker);
    return sneaker;
  }

  public List<Sneaker> getSneakersByBrand(Brand brand) {
    return sneakerRepo.findSneakersByBrand(brand).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Brand name is false choose one of the possible ones", 404));
  }

  public Sneaker updateSneakerByID(long id, SneakerRequest sneakerRequest) {
    Sneaker sneaker = sneakerRepo.findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "sneaker not found", 404));

    sneaker.setName(sneakerRequest.getName());
    sneaker.setBrand(sneakerRequest.getBrand());
    sneaker.setReleaseYear(sneakerRequest.getReleaseYear());
    return sneaker;
  }
}
