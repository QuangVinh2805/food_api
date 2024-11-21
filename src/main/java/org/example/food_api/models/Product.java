package org.example.food_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    private Shop shop;

    @Size(max = 200)
    @NotNull
    @Column(name = "product_name", nullable = false, length = 200)
    private String productName;

    @Size(max = 200)
    @Column(name = "rate", length = 200)
    private String rate;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Size(max = 100)
    @Column(name = "image", length = 100)
    private String Image;

}