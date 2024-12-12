package org.example.food_api.services;


import org.example.food_api.models.*;
import org.example.food_api.repository.*;
import org.example.food_api.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender emailSender;

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
        Long status = request.getStatus();
        String address = request.getAddress();

        User user = userRepository.findByUserId(userId);
        Order order = orderRepository.findOrderById(orderId);
        if (user == null) {
            String message = "User null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }

        if (order == null) {
            String message = "Order null";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }
        order.setStatus(status);
        orderRepository.save(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrder(order);

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

        String cartDetailIds = request.getCartDetailIds();
        if (cartDetailIds == null || cartDetailIds.isEmpty()) {
            String message = "Bạn chưa chọn sản phẩm để thanh toán";
            System.out.println(message);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);
        }

        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append("Chúc mừng bạn đã đặt hàng thành công").append("\n");

        List<OrderDetail> orderDetails = new ArrayList<>();
        List<CartDetail> cartDetails = cartDetailRepository.findByUserId(userId);
        Long totalPrice = 0L;
        Order order = Order.builder()
                .user(user)
                .address(request.getAddress())
                .createdAt(new Date())
                .build();
        orderRepository.save(order);

        List<Cart> carts = new ArrayList<>();
        //Chay vong for cart detail để thêm vào order
        for (int i = 0; i < cartDetails.size(); i++) {
            CartDetail cartDetail = cartDetails.get(i);
            //Lấy ra sản phẩm trong cart detail
            if (cartDetailIds.contains(cartDetail.getId().toString())) {
                Product product = cartDetail.getCart().getProduct();
                carts.add(cartDetail.getCart());
                emailMessage.append("- ");
                emailMessage.append(cartDetail.getQuantity()).append(" ");
                emailMessage.append(product.getProductName());
                emailMessage.append(":");
                emailMessage.append(cartDetail.getTotalPrice()).append(" VND");
                emailMessage.append("\n");

                OrderDetail orderDetail = OrderDetail.builder()
                        .productDetail(productDetailRepository.findByProductId(product.getId()))
                        .order(order)
                        .quantity(cartDetail.getQuantity())
                        .totalPrice(cartDetail.getTotalPrice())
                        .createdAt(new Date())
                        .build();
                orderDetails.add(orderDetail);
                totalPrice += orderDetail.getTotalPrice();
                orderDetailRepository.saveAll(orderDetails);
            }
        }
        emailMessage.append("Tổng tiền đơn hàng: ").append(totalPrice);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinhdaumoi2805.com");
//        if (user.getEmail() == null) {
//            user.setEmail("khanhlhfw@gmail.com");
//        }
        message.setTo(user.getEmail());
        message.setSubject("Thông báo đặt hàng thành công");
        message.setText(emailMessage.toString());

        new Thread(() -> emailSender.send(message)).start();
        cartRepository.deleteAll(carts);

        return new ResponseEntity(orderDetails, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderDetail>> getOrders(Long userId) {
        List<OrderDetail> orderDetails;
        orderDetails = orderDetailRepository.getOrderDetailsByUserId(userId);
        if (orderDetails.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderDetails);
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
