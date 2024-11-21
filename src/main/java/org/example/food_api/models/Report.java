package org.example.food_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @NotNull
    @Column(name = "quantity_order", nullable = false)
    private Integer quantityOrder;

    @NotNull
    @Column(name = "revenue", nullable = false, precision = 15, scale = 2)
    private BigDecimal revenue;

    @NotNull
    @Column(name = "profit", nullable = false, precision = 15, scale = 2)
    private BigDecimal profit;

}