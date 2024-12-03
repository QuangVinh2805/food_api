package org.example.food_api.repository;

import org.example.food_api.models.Banner;
import org.example.food_api.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BannerRepository extends JpaRepository<Banner,Long> {
    Banner findById(long id);

    @Query(value = "select b.id from Banner b where b.id = :id",nativeQuery = false)
    Long findIdByBannerId(@Param("id") Long id);
}
