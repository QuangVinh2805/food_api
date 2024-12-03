package org.example.food_api.services;

import org.example.food_api.models.Discount;
import org.example.food_api.models.Discount;
import org.example.food_api.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public ResponseEntity<List<Discount>> listAllDiscount(){
        List<Discount> listDiscount= discountRepository.findAll();
        if(listDiscount.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Discount>>(listDiscount, HttpStatus.OK);
    }
    public void save(Discount discount){
        discountRepository.save(discount);
    }
    public ResponseEntity<Discount> updateDiscount(Discount discount){
        Long discountId = discountRepository.findIdByDiscountId(discount.getId());
        if (discountId == null){
            String message = "discount id not found";
            System.out.println(message + discountId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        discountRepository.save(discount);
        return new ResponseEntity(discount, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteDiscount(Long id){
        Long discountId = discountRepository.findIdByDiscountId(id);
        if (discountId == null){
            String message = "discount id not found";
            System.out.println(message + discountId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        discountRepository.deleteById(discountId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
