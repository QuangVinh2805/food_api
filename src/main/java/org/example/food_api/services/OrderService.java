package org.example.food_api.services;


import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
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

    public ResponseEntity<List<OrderDetail>> listAllOrder(){
        List<OrderDetail> listOrder= orderRepository.findAll();
        if(listOrder.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OrderDetail>>(listOrder, HttpStatus.OK);
    }
}
