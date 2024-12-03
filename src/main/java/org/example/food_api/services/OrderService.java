package org.example.food_api.services;


import org.example.food_api.models.Order;
import org.example.food_api.models.OrderDetail;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.models.User;
import org.example.food_api.repository.OrderDetailRepository;
import org.example.food_api.repository.OrderRepository;
import org.example.food_api.repository.ProductDetailRepository;
import org.example.food_api.repository.UserRepository;
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
        List<Long> productDetailIds = request.getProductDetailId();
        Long orderQuantity = request.getQuantity();
        String address = request.getAddress();

        if (userId == null || productDetailIds.isEmpty() || orderQuantity == null) {
            String message = "Customer or product or orderQuantity null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }
        if (orderQuantity <= 0) {
            String message = "Số lượng phải lớn hơn 0";
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
        for (Long productDetailId : productDetailIds) {
            ProductDetail productDetail = productDetailRepository.findByProductId(productDetailId);
            if (productDetail == null) {
                String message = "product null";
                System.out.println(message);
                return new ResponseEntity(message, HttpStatus.FORBIDDEN);
            }

            if (orderQuantity > productDetail.getQuantity()) {
                String message = "Hết hàng";
                System.out.println(message);
                return new ResponseEntity(message, HttpStatus.FORBIDDEN);
            }
            productDetail.setQuantity(productDetail.getQuantity() - orderQuantity);
            productDetailRepository.save(productDetail);
            Long totalPrice = orderQuantity * productDetail.getPrice();

            Order order = orderRepository.findOrderById(request.getOrderId());
            if (order == null) {
                String message = "Đơn hàng không tồn tại";
                System.out.println(message);
                return new ResponseEntity(message, HttpStatus.FORBIDDEN);
            }
            order.setTotalPrice(totalPrice);
            order.setAddress(address);

            OrderDetail orderDetail = orderDetailRepository.findOrderDetailByOrder(order);
            orderDetail.setTotalPrice(totalPrice);
            orderDetail.setQuantity(orderQuantity);
            orderDetail.setProductDetail(productDetail);
            orderDetails.add(orderDetail);
            orderDetailRepository.saveAll(orderDetails);
        }


        return new ResponseEntity(orderDetails, HttpStatus.OK);
    }

    public ResponseEntity<OrderDetail> createOrder(OrderRequest request) {
        Long userId = request.getUserId();
        List<Long> productDetailIds = request.getProductDetailId();
        Long orderQuantity = request.getQuantity();
        String address = request.getAddress();

        if (userId == null || productDetailIds.isEmpty() || orderQuantity == null) {
            String message = "Customer or product or orderQuantity null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }
        if (orderQuantity <= 0) {
            String message = "Số lượng phải lớn hơn 0";
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

        for (Long productDetailId : productDetailIds) {
            ProductDetail productDetail = productDetailRepository.findByProductId(productDetailId);
            if (productDetail == null) {
                String message = "product null";
                System.out.println(message);
                return new ResponseEntity(message, HttpStatus.FORBIDDEN);
            }

            if (orderQuantity > productDetail.getQuantity()) {
                String message = "Hết hàng";
                System.out.println(message);
                return new ResponseEntity(message, HttpStatus.FORBIDDEN);
            }
            productDetail.setQuantity(productDetail.getQuantity() - orderQuantity);
            productDetailRepository.save(productDetail);


            Long totalPrice = orderQuantity * productDetail.getPrice();
            Order order = Order.builder()
                    .createdAt(new Date())
                    .totalPrice(totalPrice)
                    .address(address)
                    .user(user)
                    .build();
            orderRepository.save(order);

            OrderDetail orderDetail = OrderDetail.builder()
                    .createdAt(new Date())
                    .totalPrice(totalPrice)
                    .productDetail(productDetail)
                    .quantity(orderQuantity)
                    .order(order)
                    .build();
            orderDetails.add(orderDetail);
            orderDetailRepository.saveAll(orderDetails);
        }

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
