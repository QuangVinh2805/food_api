package org.example.food_api.controller;

import org.example.food_api.models.Cart;
import org.example.food_api.models.CartDetail;
import org.example.food_api.models.Product;
import org.example.food_api.services.CartService;
import org.example.food_api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    CartService cartService;

    public static Logger logger = LoggerFactory.getLogger(CartController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<CartDetail>> listAllCart(){
        return cartService.listAllCart();
    }
}
