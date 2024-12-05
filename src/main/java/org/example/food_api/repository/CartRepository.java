package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Cart;
import org.example.food_api.models.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Override
    List<Cart> findAll();

    Cart findById(long id);

    @Query(value = "select c.id from Cart c where c.id = :id", nativeQuery = false)
    Long findIdByCartId(@Param("id") Long id);

    Cart findByUserIdAndProductId(Long userId, Long productId);

    List<Cart> findByUserId(Long userId);
}
