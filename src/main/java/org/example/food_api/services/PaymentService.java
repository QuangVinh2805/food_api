package org.example.food_api.services;


import org.example.food_api.models.Payment;
import org.example.food_api.models.Payment;
import org.example.food_api.models.User;
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
    public void save(Payment payment){
        paymentRepository.save(payment);
    }
    public ResponseEntity<Payment> updatePayment(Payment payment){
        Long paymentId = paymentRepository.findIdByPaymentId(payment.getId());
        if (paymentId == null){
            String message = "payment id not found";
            System.out.println(message + paymentId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        paymentRepository.save(payment);
        return new ResponseEntity(payment, HttpStatus.OK);
    }


    public ResponseEntity<String> deletePayment(Long id){
        Long paymentId = paymentRepository.findIdByPaymentId(id);
        if (paymentId == null){
            String message = "payment id not found";
            System.out.println(message + paymentId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        paymentRepository.deleteById(paymentId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
