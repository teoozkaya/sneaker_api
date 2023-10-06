package com.example.sneakers.service;

import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import com.example.sneakers.enums.Model;
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
            model(sneakerRequest.getModel()).
            //price(sneakerRequest.getPrice()).
            releaseYear(sneakerRequest.getReleaseYear()).
            fullName(createFullName(sneakerRequest.getBrand(), sneakerRequest.getModel(),sneakerRequest.getName())).
            build();
    if (sneaker.getModel().correctModel(sneaker)) {
    sneakerRepo.save(sneaker);
    return sneaker;
    } else {
      throw new GenericException(HttpStatus.NOT_FOUND, "model is not found", 404);
    }
  }

  public List<Sneaker> getSneakersByBrand(Brand brand) {
    return sneakerRepo.findSneakersByBrand(brand).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Brand name is false choose one of the possible ones", 404));
  }

  public List<Sneaker> getSneakersByRelaseYearDesc() {
    return null;
    //sneakerRepo.findAllByOrderByReleaseYearDesc();
  }

  public List<Sneaker> getSneakersByModel(Model model) {
    return sneakerRepo.findSneakersByModel(model).orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "Brand name is false choose one of the possible ones", 404));
  }

  public Sneaker updateSneakerByID(long id, SneakerRequest sneakerRequest) {
    Sneaker sneaker = sneakerRepo.findById(id)
            .orElseThrow(() -> new GenericException(HttpStatus.NOT_FOUND, "sneaker not found", 404));

    sneaker.setName(sneakerRequest.getName());
    sneaker.setBrand(sneakerRequest.getBrand());
    sneaker.setReleaseYear(sneakerRequest.getReleaseYear());
    sneaker.setModel(sneakerRequest.getModel());
    sneaker.setPrice(sneakerRequest.getPrice());
    sneaker.setFullName(createFullName(sneaker.getBrand(), sneaker.getModel(),sneaker.getName()));
    sneakerRepo.save(sneaker);
    return sneaker;
  }

  public void deleteAllSneakers() {
    sneakerRepo.deleteAll();
  }

  public void deleteSneakerById(long id) {
    sneakerRepo.deleteById(id);
  }

  public List<Sneaker> search(String name) {
    return null;
  }

  public String createFullName(Brand brand, Model model, String name) {
    return brand.toString() + model.toString() + name;
  }
}
