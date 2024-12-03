package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Query(value = "select c.id from Customer c where c.id = :id",nativeQuery = false)
    Long findIdByCustomerId(@Param("id") Long id);

    Customer findByCustomerName(String customerName);

    @Query(value = "select c.customerName from Customer c where c.customerName = :customerName", nativeQuery = false)
    String findCustomerName(@Param("customerName") String customerName);

    @Query(value = "select customer_name from customer where customer_name = :customerName", nativeQuery = true)
    String findCustomerNameNativeQuery(@Param("customerName") String customerName);
}
