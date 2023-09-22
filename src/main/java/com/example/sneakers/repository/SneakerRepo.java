package com.example.sneakers.repository;

import com.example.sneakers.entity.Sneaker;
import com.example.sneakers.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SneakerRepo extends JpaRepository<Sneaker, Long> {

  Optional<List<Sneaker>> findSneakersByBrand(Brand brand);


}
