package org.example.food_api.controller;

import org.example.food_api.models.Payment;
import org.example.food_api.models.Payment;
import org.example.food_api.models.Payment;
import org.example.food_api.repository.PaymentRepository;
import org.example.food_api.services.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    public static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private PaymentRepository paymentRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> listAllPayment(){
        return paymentService.listAllPayment();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable("id") long id) {
        return paymentService.deletePayment(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllPayment() {
        try {
            paymentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
        return paymentService.updatePayment(payment);
    }
    @PostMapping("/create")
    public void createPayment(@RequestBody Payment payment) {
        paymentService.save(payment);
    }
}
