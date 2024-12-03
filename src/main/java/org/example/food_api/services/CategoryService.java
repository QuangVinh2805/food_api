package org.example.food_api.services;


import org.example.food_api.models.*;
import org.example.food_api.models.Category;
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
    public void save(Category category){
        categoryRepository.save(category);
    }
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
    public ResponseEntity<Category> createCategory(Category category){
        Category categorydb = categoryRepository.findByCategoryName(category.getCategoryName());
        if (categorydb != null){
            String message = "category name already exists";
            System.out.println(message + categorydb.getCategoryName());
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        categoryRepository.save(category);
        return new ResponseEntity(category, HttpStatus.OK);
    }


    public ResponseEntity<Category> updateCategory(Category category){
        Long categoryId = categoryRepository.findIdByCategoryId(category.getId());
        if (categoryId == null){
            String message = "category id not found";
            System.out.println(message + categoryId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        categoryRepository.save(category);
        return new ResponseEntity(category, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteCategory(Long id){
        Long categoryId = categoryRepository.findIdByCategoryId(id);
        if (categoryId == null){
            String message = "category id not found";
            System.out.println(message + categoryId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        categoryRepository.deleteById(categoryId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
