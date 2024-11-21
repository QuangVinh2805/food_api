package org.example.food_api.controller;

import org.example.food_api.models.Payment;
import org.example.food_api.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    public static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllPayment(){
        return paymentService.listAllPayment();
    }
}
