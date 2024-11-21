package org.example.food_api.repository;

import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
    @Override
    List<OrderDetail> findAll();
}
