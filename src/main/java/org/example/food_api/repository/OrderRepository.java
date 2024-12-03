package org.example.food_api.repository;

import org.example.food_api.models.Order;
import org.example.food_api.models.Cart;
import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    List<Order> findAll();
    Order findById(long id);

    @Query(value = "select o.id from Order o where o.id = :id",nativeQuery = false)
    Long findIdByOrderId(@Param("id") Long id);
}
