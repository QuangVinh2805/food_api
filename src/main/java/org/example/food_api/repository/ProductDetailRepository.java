package org.example.food_api.repository;

import org.example.food_api.models.Product;
import org.example.food_api.models.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Long countProductDeTailByStatus(Long status);

    @Query(value = "select * from product_detail p where p.product_id = :id", nativeQuery = true)
    ProductDetail findByProductId(@Param("id") Long id);

//    @Query("SELECT p FROM ProductDetail p WHERE p.product = :productIdStr")
//    ProductDetail findProductDetailByProductId(@Param("product") long productStr);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.product.id = :productId")
    List<ProductDetail> findProductDetailsByProductId(@Param("productId") long productId);

    Page<ProductDetail> findByStatus(Pageable pageable, Long status);

    @Query("SELECT DISTINCT pd FROM ProductDetail pd WHERE " +
            "LOWER(pd.product.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(pd.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ProductDetail> findProductDetail(@Param("keyword") String keyword);

}
