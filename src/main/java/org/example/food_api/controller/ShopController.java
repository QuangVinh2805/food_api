package org.example.food_api.controller;

import org.example.food_api.models.Shop;
import org.example.food_api.services.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    public static Logger logger = LoggerFactory.getLogger(ShopController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Shop>> listAllShop(){
        return shopService.listAllShop();
    }
}
