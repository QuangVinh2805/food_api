package org.example.food_api.services;


import org.example.food_api.models.Category;
import org.example.food_api.models.Product;
import org.example.food_api.repository.CategoryRepository;
import org.example.food_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<List<Category>> listAllCategory(){
        List<Category> listCategory= categoryRepository.findAll();
        if(listCategory.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(listCategory, HttpStatus.OK);
    }
}
