package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();
}
