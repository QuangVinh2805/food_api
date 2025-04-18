package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();

    Product findById(long id);

    Long countProductsByStatus(Long status);

    @Query(value = "select p.id from Product p where p.id = :id", nativeQuery = false)
    Long findIdByProductId(@Param("id") Long id);

    Product findByProductName(String productName);

    @Query(value = "select p from Product p where p.id = :id", nativeQuery = false)
    Product findByProductId(@Param("id") Long id);

    @Query(value = "select p from Product p order by p.createAt desc", nativeQuery = false)
    List<Product> findAllOrderByCreateAtDesc();
}
