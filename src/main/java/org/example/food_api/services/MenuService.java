package org.example.food_api.services;


import org.example.food_api.models.Menu;
import org.example.food_api.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuRepository menuRepository;

    public ResponseEntity<List<Menu>> listAllMenu(){
        List<Menu> listMenu= menuRepository.findAll();
        if(listMenu.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Menu>>(listMenu, HttpStatus.OK);
    }
}
