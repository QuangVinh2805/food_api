package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartDetail,Long> {
    @Override
    List<CartDetail> findAll();
}
