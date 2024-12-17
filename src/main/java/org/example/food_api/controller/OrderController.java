package org.example.food_api.controller;

import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.example.food_api.models.Order;
import org.example.food_api.repository.OrderRepository;
import org.example.food_api.request.OrderRequest;
import org.example.food_api.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    public static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllOrder() {
        return orderService.listAllOrder();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") long id) {
        return orderService.deleteOrder(id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<OrderDetail>> getOrders(@PathVariable("id") long id) {
        return orderService.getOrders(id);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllOrder() {
        try {
            orderRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDetail> updateOrder(@RequestBody OrderRequest request) {
        Date updatedAt = request.getUpdatedAt();
        System.out.println("Ngày cập nhật: " + updatedAt); // Đã parse thành Date
        return orderService.updateOrder(request);
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDetail> createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }
}
