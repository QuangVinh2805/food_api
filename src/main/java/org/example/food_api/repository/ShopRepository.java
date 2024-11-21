package org.example.food_api.repository;

import org.example.food_api.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
    @Override
    List<Shop> findAll();
}
