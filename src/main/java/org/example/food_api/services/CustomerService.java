package org.example.food_api.services;


import org.example.food_api.models.Customer;
import org.example.food_api.models.Customer;
import org.example.food_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public void save(Customer customer){
        customerRepository.save(customer);
    }
    public void delete(Long id){
        customerRepository.deleteById(id);
    }
    public ResponseEntity<Customer> createCustomer(Customer customer){
        Customer customerdb = customerRepository.findByCustomerName(customer.getCustomerName());
        String customerName1 = customerRepository.findCustomerName(customer.getCustomerName());
        String customerName2 = customerRepository.findCustomerNameNativeQuery(customer.getCustomerName());
        if (customerdb != null){
            String message = "customer name already exists";
            System.out.println(message + customerdb.getCustomerName());
            System.out.println(message + customerName1);
            System.out.println(message + customerName2);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
//        customer.setId(null);
        customerRepository.save(customer);
        return new ResponseEntity(customer, HttpStatus.OK);
    }


    public ResponseEntity<Customer> updateCustomer(Customer customer){
        Long customerId = customerRepository.findIdByCustomerId(customer.getId());
        if (customerId == null){
            String message = "customer id not found";
            System.out.println(message + customerId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        customerRepository.save(customer);
        return new ResponseEntity(customer, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteCustomer(Long id){
        Long customerId = customerRepository.findIdByCustomerId(id);
        if (customerId == null){
            String message = "customer id not found";
            System.out.println(message + customerId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        customerRepository.deleteById(customerId);
        return new ResponseEntity(HttpStatus.OK);
    }


}
