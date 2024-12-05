package org.example.food_api.controller;

import org.example.food_api.models.*;
import org.example.food_api.models.Cart;
import org.example.food_api.models.Cart;
import org.example.food_api.repository.CartRepository;
import org.example.food_api.request.CartRequest;
import org.example.food_api.services.CartService;
import org.example.food_api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    CartService cartService;

    public static Logger logger = LoggerFactory.getLogger(CartController.class);
    @Autowired
    private CartRepository cartRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<CartDetail>> listAllCart(){
        return cartService.listAllCart();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") long id) {
        return cartService.deleteCart(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllCart() {
        try {
            cartRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<CartDetail> updateCart(@RequestBody CartRequest cartRequest) {
        return cartService.createCart(cartRequest);
    }
    @PostMapping("/create")
    public ResponseEntity<CartDetail> createCart(@RequestBody CartRequest cartRequest) {
        return cartService.createCart(cartRequest);
    }
}
