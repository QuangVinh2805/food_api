package org.example.food_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    @Column(name = "discount_name", length = 200)
    private String discountName;

    @Lob
    @Column(name = "discount_type")
    private String discountType;

    @NotNull
    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountValue;

    @NotNull
    @Column(name = "discount_order_minimum", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountOrderMinimum;

    @NotNull
    @Column(name = "discount_maximum", nullable = false, precision = 10, scale = 2)
    private BigDecimal discountMaximum;

    @NotNull
    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    @Lob
    @Column(name = "discount_description")
    private String discountDescription;

}