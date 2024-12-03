package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Override
    List<ProductDetail> findAll();

    List<ProductDetail> findByIsHot(Long isHot);

    @Query(value = "select p from ProductDetail p where p.id = :id",nativeQuery = false)
    ProductDetail findByProductId(@Param("id") Long id);
}
