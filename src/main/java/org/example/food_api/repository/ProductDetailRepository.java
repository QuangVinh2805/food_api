package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Override
    List<ProductDetail> findAll();
}
