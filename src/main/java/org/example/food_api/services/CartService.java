package org.example.food_api.services;

import org.example.food_api.models.*;
import org.example.food_api.models.Cart;
import org.example.food_api.repository.*;
import org.example.food_api.request.CartRequest;
import org.hibernate.boot.query.HbmResultSetMappingDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public ResponseEntity<List<Cart>> listAllCart(){
        List<Cart> listCart= cartRepository.findAll();
        if(listCart.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cart>>(listCart, HttpStatus.OK);
    }
    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public ResponseEntity<CartDetail> createCart(CartRequest cartRequest){
        Long userId = cartRequest.getUserId();
        Long productId = cartRequest.getProductId();
        Long quantity = cartRequest.getQuantity();
        ProductDetail productDetail = productDetailRepository.findByProductId(productId);
        Long totalPrice = productDetail.getPrice() * quantity;
        Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
        User user = userRepository.findByUserId(userId);
        Product product = productRepository.findByProductId(productId);
        CartDetail cartDetail = null;
        if(cart == null) {
            cart = new Cart(null,user,new Date(),null,totalPrice,product);
            cartDetail = new CartDetail(null,cart,quantity,productDetail.getPrice(),totalPrice,new Date(),null);
        } else{
            cartDetail = cartDetailRepository.findByCartId(cart.getId());
            cart.setTotalPrice(totalPrice);
            cartDetail.setTotalPrice(totalPrice);
            cartDetail.setQuantity(quantity);
        }
        cartRepository.save(cart);
        cartDetailRepository.save(cartDetail);
        return new ResponseEntity<CartDetail>(cartDetail, HttpStatus.OK);
    }
    public ResponseEntity<Cart> updateCart(Cart cart){
        Long cartId = cartRepository.findIdByCartId(cart.getId());
        if (cartId == null){
            String message = "cart id not found";
            System.out.println(message + cartId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        cartRepository.save(cart);
        return new ResponseEntity(cart, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteCart(Long id){
        Long cartId = cartRepository.findIdByCartId(id);
        if (cartId == null){
            String message = "cart id not found";
            System.out.println(message + cartId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        cartRepository.deleteById(cartId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
