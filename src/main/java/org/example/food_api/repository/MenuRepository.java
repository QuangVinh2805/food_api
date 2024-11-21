package org.example.food_api.repository;

import org.example.food_api.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long> {
    @Override
    List<Menu> findAll();
}
