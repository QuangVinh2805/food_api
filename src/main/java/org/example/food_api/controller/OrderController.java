package org.example.food_api.controller;

import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.example.food_api.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> listAllOrder(){
        return orderService.listAllOrder();
    }
}
