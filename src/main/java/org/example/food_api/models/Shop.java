package org.example.food_api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shop")
public class Shop implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 200)
    @NotNull
    @Column(name = "shop_name", nullable = false, length = 200)
    private String shopName;

    @Size(max = 200)
    @Column(name = "shop_image", length = 200)
    private String shopImage;

    @Size(max = 100)
    @Column(name = "shop_address", length = 100)
    private String shopAddress;

    @Column(name = "open_time")
    private Instant openTime;

    @Column(name = "close_time")
    private Instant closeTime;

}