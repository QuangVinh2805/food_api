package org.example.food_api.repository;

import org.example.food_api.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Long> {
    Banner findById(long id);
}
