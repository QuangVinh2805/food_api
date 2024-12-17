package org.example.food_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Size(max = 200)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Size(max = 200)
    @Column(name = "rate", length = 200)
    private Long rate;

    @Size(max = 1)
    @Column(name = "status", length = 1,nullable = false)
    private Long status = 1L;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Size(max = 100)
    @Column(name = "image", length = 100)
    private String image;


}