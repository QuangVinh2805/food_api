package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.example.food_api.request.ProductDetailRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Override
    List<ProductDetail> findAll();

    ProductDetail findById(long id);

    List<ProductDetail> findPDByProductId(long productId);

    List<ProductDetail> findByIsHot(Long isHot);

    List<ProductDetail> findByProduct(Product product);

    @Query(value = "select p from ProductDetail p where p.id = :id",nativeQuery = false)
    ProductDetail findByProductId(@Param("id") Long id);

//    @Query("SELECT p FROM ProductDetail p WHERE p.product = :productIdStr")
//    ProductDetail findProductDetailByProductId(@Param("product") long productStr);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<ProductDetail> findProductDetailsByProductId(@Param("productId") long productId);

}
