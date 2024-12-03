package org.example.food_api.controller;

import org.example.food_api.models.Discount;
import org.example.food_api.models.Discount;
import org.example.food_api.repository.DiscountRepository;
import org.example.food_api.services.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;

    public static Logger logger = LoggerFactory.getLogger(DiscountController.class);
    @Autowired
    private DiscountRepository discountRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Discount>> listAllDiscount(){
        return discountService.listAllDiscount();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDiscount(@PathVariable("id") long id) {
        return discountService.deleteDiscount(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllDiscount() {
        try {
            discountRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Discount> updateDiscount(@RequestBody Discount discount) {
        return discountService.updateDiscount(discount);
    }
    @PostMapping("/create")
    public void createDiscount(@RequestBody Discount discount) {
        discountService.save(discount);
    }
}
