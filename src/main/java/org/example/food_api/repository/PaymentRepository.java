package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Override
    List<Payment> findAll();
    Payment findById(long id);
    @Query(value = "select p.id from Payment p where p.id = :id",nativeQuery = false)
    Long findIdByPaymentId(@Param("id") Long id);
}
