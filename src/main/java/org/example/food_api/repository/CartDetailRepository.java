package org.example.food_api.repository;

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

    CartDetail findCartDetailById(long id);

    @Query(value = "select c.id from CartDetail c where c.id = :id", nativeQuery = false)
    Long findIdByCartDetailId(@Param("id") Long id);

    @Query(value = "select * from cart_detail cd where cart_id in (select id from cart where user_id = :userId)", nativeQuery = true)
    List<CartDetail> findByUserId(@Param("userId") Long userId);

    CartDetail findByCartId(Long cartId);
}
