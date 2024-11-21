package org.example.food_api.controller;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.models.User;
import org.example.food_api.services.ProductService;
import org.example.food_api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    public static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProduct(){
        return productService.listAllProduct();
    }

    @RequestMapping(value = "/alldetail", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDetail>> listAllProductDetail(){
        return productService.listAllProductDetail();
    }

}
