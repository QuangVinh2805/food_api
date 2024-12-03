package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Override
    List<Shop> findAll();
    Shop findById(long id);
    @Query(value = "select s.id from Shop s where s.id = :id",nativeQuery = false)
    Long findIdByShopId(@Param("id") Long id);
}
