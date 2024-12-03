package org.example.food_api.controller;

import org.example.food_api.models.Shop;
import org.example.food_api.models.Shop;
import org.example.food_api.models.Shop;
import org.example.food_api.repository.ShopRepository;
import org.example.food_api.services.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    public static Logger logger = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    private ShopRepository shopRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Shop>> listAllShop(){
        return shopService.listAllShop();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable("id") long id) {
        return shopService.deleteShop(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllShop() {
        try {
            shopRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
        return shopService.updateShop(shop);
    }
    @PostMapping("/create")
    public void createShop(@RequestBody Shop shop) {
        shopService.save(shop);
    }
}
