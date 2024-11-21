package org.example.food_api.services;


import org.example.food_api.models.Shop;
import org.example.food_api.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public ResponseEntity<List<Shop>> listAllShop(){
        List<Shop> listShop= shopRepository.findAll();
        if(listShop.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shop>>(listShop, HttpStatus.OK);
    }}
