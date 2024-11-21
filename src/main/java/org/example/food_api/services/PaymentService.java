package org.example.food_api.services;


import org.example.food_api.models.Payment;
import org.example.food_api.repository.PaymentRepository;
import org.example.food_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired

    PaymentRepository paymentRepository;

    public ResponseEntity<List<Payment>> listAllPayment(){
        List<Payment> listPayment= paymentRepository.findAll();
        if(listPayment.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Payment>>(listPayment, HttpStatus.OK);
    }
}
