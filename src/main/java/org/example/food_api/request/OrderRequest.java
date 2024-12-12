package org.example.food_api.request;


import lombok.Data;

@Data
public class OrderRequest {
    private Long orderId;
    private String cartDetailIds;
    private Long userId;
    private String address;
    private Long status;
}
