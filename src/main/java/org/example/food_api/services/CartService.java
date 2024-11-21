package org.example.food_api.services;

import org.example.food_api.models.Cart;
import org.example.food_api.models.CartDetail;
import org.example.food_api.models.Product;
import org.example.food_api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public ResponseEntity<List<CartDetail>> listAllCart(){
        List<CartDetail> listCart= cartRepository.findAll();
        if(listCart.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CartDetail>>(listCart, HttpStatus.OK);
    }
}
