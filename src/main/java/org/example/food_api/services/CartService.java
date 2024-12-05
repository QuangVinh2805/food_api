package org.example.food_api.services;

import org.example.food_api.models.*;
import org.example.food_api.repository.*;
import org.example.food_api.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    public ResponseEntity<List<CartDetail>> listAllCart() {
        List<CartDetail> listCart = cartDetailRepository.findAll();
        if (listCart.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CartDetail>>(listCart, HttpStatus.OK);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public ResponseEntity<List<CartDetail>> listCartByUser(Long userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        List<CartDetail> cartDetails = new ArrayList<>();

        for (Cart cart : carts) {
            CartDetail cartDetail = cartDetailRepository.findByCartId(cart.getId());
            cartDetails.add(cartDetail);
        }

        if (cartDetails.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CartDetail>>(cartDetails, HttpStatus.OK);
    }

    public ResponseEntity<CartDetail> createCart(CartRequest cartRequest) {
        Long userId = cartRequest.getUserId();
        Long productId = cartRequest.getProductId();
        Long quantity = cartRequest.getQuantity();

        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        User user = userRepository.findByUserId(userId);
        Product product = productRepository.findByProductId(productId);
        CartDetail cartDetail = null;
        Long totalPrice = null;
        if (cart == null) {
            totalPrice = productDetail.getPrice() * quantity;
            cart = new Cart(null, user, new Date(), null, totalPrice, product);
        }
        cartDetail = cartDetailRepository.findByCartId(cart.getId());
        if (cartDetail == null){
            cartDetail = new CartDetail(null, cart, quantity, productDetail.getPrice(), totalPrice, new Date(), null);
            cartDetail.setQuantity(quantity);
        }else{
            cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
        }
        totalPrice = productDetail.getPrice() * cartDetail.getQuantity();
        cart.setTotalPrice(totalPrice);
        cartDetail.setTotalPrice(totalPrice);

        cartRepository.save(cart);
        cartDetailRepository.save(cartDetail);
        return new ResponseEntity<CartDetail>(cartDetail, HttpStatus.OK);
    }

    public ResponseEntity<CartDetail> updateCart(CartRequest cartRequest) {
        Long cardDetailId = cartRequest.getId();
        Long quantity = cartRequest.getQuantity();

        String errorMessage = "";
        if (cardDetailId == null) {
            errorMessage = "cart id not found";
            System.out.println(errorMessage + cardDetailId);
            return new ResponseEntity(errorMessage, HttpStatus.FORBIDDEN);
        }

        CartDetail cartDetail = cartDetailRepository.findCartDetailById(cardDetailId);
        if (cartDetail == null) {
            errorMessage = "cart id not found";
            System.out.println(errorMessage + cardDetailId);
            return new ResponseEntity(errorMessage, HttpStatus.FORBIDDEN);
        }

        Cart cart = cartDetail.getCart();
        ProductDetail productDetail = productDetailRepository.findByProductId(cart.getProduct().getId());
        Product product = productRepository.findByProductId(cart.getProduct().getId());
        if (quantity <= 0) {
            cartDetailRepository.delete(cartDetail);
            cartRepository.delete(cart);
        }
        if (quantity > productDetail.getQuantity()) {
            errorMessage = "Quá số lượng quy định";
            System.out.println(errorMessage + cardDetailId);
            return new ResponseEntity(errorMessage, HttpStatus.FORBIDDEN);
        }
        cartDetail.setQuantity(quantity);
        Long totalPrice = quantity * productDetail.getPrice();
        cartDetail.setTotalPrice(totalPrice);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
        cartDetailRepository.save(cartDetail);
        return new ResponseEntity(cartDetail, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteCart(Long id) {
        if (id == null) {
            String message = "cartDetail id not found";
            System.out.println(message + id);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        CartDetail cartDetail = cartDetailRepository.findCartDetailById(id);
        cartRepository.delete(cartDetail.getCart());
        return new ResponseEntity(HttpStatus.OK);
    }
}
