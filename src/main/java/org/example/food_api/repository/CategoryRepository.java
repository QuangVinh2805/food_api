package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Category;
import org.example.food_api.models.Category;
import org.example.food_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    List<Category> findAll();

    Category findCategoryById(long id);

    @Query(value = "select c.id from Category c where c.id = :id",nativeQuery = false)
    Long findIdByCategoryId(@Param("id") Long id);

    Category findByCategoryName(String categoryName);
}
