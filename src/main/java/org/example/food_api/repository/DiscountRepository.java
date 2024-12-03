package org.example.food_api.repository;


import org.example.food_api.models.Discount;
import org.example.food_api.models.Discount;
import org.example.food_api.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
    @Override
    List<Discount> findAll();
    Discount findById(long id);

    @Query(value = "select d.id from Discount d where d.id = :id",nativeQuery = false)
    Long findIdByDiscountId(@Param("id") Long id);

}
