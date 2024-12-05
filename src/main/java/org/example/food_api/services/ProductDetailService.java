package org.example.food_api.services;


import org.example.food_api.models.ProductDetail;
import org.example.food_api.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    public ProductDetail findProductDetailByProductId(@PathVariable("id") long id) {
        ProductDetail contact = productDetailRepository.findById(id);
        if (contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }
}
