package org.example.food_api.repository;

import org.example.food_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Override
    List<Category> findAll();
}
