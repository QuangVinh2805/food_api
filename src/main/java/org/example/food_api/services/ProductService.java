package org.example.food_api.services;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.models.User;
import org.example.food_api.repository.ProductDetailRepository;
import org.example.food_api.repository.ProductRepository;
import org.example.food_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;

    public ResponseEntity<List<Product>> listAllProduct(){
        List<Product> listProduct= productRepository.findAll();
        if(listProduct.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(listProduct, HttpStatus.OK);
    }

    public ResponseEntity<List<ProductDetail>> listAllProductDetail(){
        List<ProductDetail> listProductDetail= productDetailRepository.findAll();
        if(listProductDetail.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductDetail>>(listProductDetail, HttpStatus.OK);
    }
}
