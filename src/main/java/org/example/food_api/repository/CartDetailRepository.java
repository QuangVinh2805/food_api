package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

    @Override
    List<CartDetail> findAll();
    CartDetail findById(long id);

    @Query(value = "select c.id from CartDetail c where c.id = :id",nativeQuery = false)
    Long findIdByCartDetailId(@Param("id") Long id);


    CartDetail findByCartId(Long cartId);
}
