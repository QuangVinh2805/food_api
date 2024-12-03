package org.example.food_api.services;


import org.example.food_api.models.Order;
import org.example.food_api.models.Order;
import org.example.food_api.models.Order;
import org.example.food_api.models.User;
import org.example.food_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ResponseEntity<List<Order>> listAllOrder(){
        List<Order> listOrder= orderRepository.findAll();
        if(listOrder.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(listOrder, HttpStatus.OK);
    }
    public void save(Order order){
        orderRepository.save(order);
    }
    public ResponseEntity<Order> updateOrder(Order order){
        Long orderId = orderRepository.findIdByOrderId(order.getId());
        if (orderId == null){
            String message = "order id not found";
            System.out.println(message + orderId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        orderRepository.save(order);
        return new ResponseEntity(order, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteOrder(Long id){
        Long orderId = orderRepository.findIdByOrderId(id);
        if (orderId == null){
            String message = "order id not found";
            System.out.println(message + orderId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        orderRepository.deleteById(orderId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
