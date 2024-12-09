package org.example.food_api.repository;

import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail findOrderDetailByOrder(Order order);

    @Query(value = "select * from order_detail od where order_id in (select id from `order` where user_id = :userId) order by order_id asc", nativeQuery = true)
    List<OrderDetail> getOrderDetailsByUserId(Long userId);
}
