package org.example.food_api.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class CartRequest {
    private Long id;

    private Long productId;

    private Long userId;

    private Long quantity;


}
