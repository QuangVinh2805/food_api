package org.example.food_api.controller;

import org.example.food_api.models.Product;
import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.models.Product;
import org.example.food_api.repository.ProductDetailRepository;
import org.example.food_api.repository.ProductRepository;
import org.example.food_api.request.ProductRequest;
import org.example.food_api.services.ProductService;
import org.example.food_api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductDetailRepository productDetailRepository;

    public static Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> listAllProduct(){
        return productService.listAllProduct();
    }

    @RequestMapping(value = "/alldetail", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDetail>> listAllProductDetail(@RequestParam(defaultValue = "0") int page) {
        int totalProducts = (int) productDetailRepository.count();
        int size = totalProducts; // Hiển thị toàn bộ sản phẩm

        return productService.listAllProductDetail(page, size);
    }
//    public ResponseEntity<List<ProductDetail>> listAllProductDetail(@RequestParam(defaultValue = "0") int page,
//                                                                    @RequestParam(defaultValue = "10") int size){
//
//        return productService.listAllProductDetail(page, size);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long id) {
        return productService.deleteProduct(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllProduct() {
        try {
            productRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productRequest);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }
    @GetMapping("/hot")
    public ResponseEntity<List<ProductDetail>> hotProduct() {
        return productService.listProductDetailByHot();
    }

}
