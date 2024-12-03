package org.example.food_api.request;


import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long orderId;
    private Long userId;
    private List<Long> productDetailId;
    private Long quantity;
    private String address;
}
