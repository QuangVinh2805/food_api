package org.example.food_api.services;


import org.example.food_api.models.Shop;
import org.example.food_api.models.Shop;
import org.example.food_api.models.User;
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
    }
    public void save(Shop shop){
        shopRepository.save(shop);
    }
    public ResponseEntity<Shop> updateShop(Shop shop){
        Long shopId = shopRepository.findIdByShopId(shop.getId());
        if (shopId == null){
            String message = "shop id not found";
            System.out.println(message + shopId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        shopRepository.save(shop);
        return new ResponseEntity(shop, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteShop(Long id){
        Long shopId = shopRepository.findIdByShopId(id);
        if (shopId == null){
            String message = "shop id not found";
            System.out.println(message + shopId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        shopRepository.deleteById(shopId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
