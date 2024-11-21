package org.example.food_api.repository;

import org.example.food_api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();
}
