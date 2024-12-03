package org.example.food_api.repository;

import org.example.food_api.models.Cart;
import org.example.food_api.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Override
    List<Report> findAll();
    Report findById(long id);
    @Query(value = "select r.id from Report r where r.id = :id",nativeQuery = false)
    Long findIdByReportId(@Param("id") Long id);
}
