package org.example.food_api.services;


import org.example.food_api.models.Customer;
import org.example.food_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<List<Customer>> listAllCustomer(){
        List<Customer> listCustomer= customerRepository.findAll();
        if(listCustomer.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Customer>>(listCustomer, HttpStatus.OK);
    }
}
