package org.example.food_api.services;


import org.example.food_api.models.*;
import org.example.food_api.repository.*;
import org.example.food_api.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    public ResponseEntity<List<Order>> listAllOrder() {
        List<Order> listOrder = orderRepository.findAll();
        if (listOrder.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(listOrder, HttpStatus.OK);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public ResponseEntity<OrderDetail> updateOrder(OrderRequest request) {
        Long orderId = request.getOrderId();
        Long userId = request.getUserId();
        String address = request.getAddress();

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            String message = "User null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }
        List<OrderDetail> orderDetails = new ArrayList<>();
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(userId);
        for (CartDetail cartDetail : cartDetails) {
            Order order = Order.builder()
                    .user(user)
                    .totalPrice(cartDetail.getTotalPrice())
                    .address(request.getAddress())
                    .createdAt(new Date())
                    .build();
            orderRepository.save(order);

            OrderDetail orderDetail = OrderDetail.builder()
                    .productDetail(productDetailRepository.findByProductId(cartDetail.getCart().getProduct().getId()))
                    .order(order)
                    .quantity(cartDetail.getQuantity())
                    .totalPrice(cartDetail.getTotalPrice())
                    .createdAt(new Date())
                    .build();
            orderDetails.add(orderDetail);
            orderDetailRepository.saveAll(orderDetails);
        }
        return new ResponseEntity(orderDetails, HttpStatus.OK);
    }

    public ResponseEntity<OrderDetail> createOrder(OrderRequest request) {
        Long userId = request.getUserId();
        String address = request.getAddress();

        if (userId == null) {
            String message = "userId null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }

        User user = userRepository.findByUserId(userId);
        if (user == null) {
            String message = "User null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(userId);
        Long totalPrice = 0L;
        Order order = Order.builder()
                .user(user)
                .address(request.getAddress())
                .createdAt(new Date())
                .build();
        orderRepository.save(order);
        for (CartDetail cartDetail : cartDetails) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .productDetail(productDetailRepository.findByProductId(cartDetail.getCart().getProduct().getId()))
                    .order(order)
                    .quantity(cartDetail.getQuantity())
                    .totalPrice(cartDetail.getTotalPrice())
                    .createdAt(new Date())
                    .build();
            orderDetails.add(orderDetail);
            totalPrice += orderDetail.getTotalPrice();
            orderDetailRepository.saveAll(orderDetails);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        List<Cart> carts = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(carts);

        return new ResponseEntity(orderDetails, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteOrder(Long id) {
        Long orderId = orderRepository.findIdByOrderId(id);
        if (orderId == null) {
            String message = "order id not found";
            System.out.println(message + orderId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        orderRepository.deleteById(orderId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
