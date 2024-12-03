package org.example.food_api.repository;

import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findOrderDetailByOrder(Order order);
}
