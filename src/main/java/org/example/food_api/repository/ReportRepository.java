package org.example.food_api.repository;

import org.example.food_api.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Override
    List<Report> findAll();
}
