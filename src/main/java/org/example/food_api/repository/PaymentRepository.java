package org.example.food_api.repository;

import org.example.food_api.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Override
    List<Payment> findAll();
}
