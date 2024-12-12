package org.example.food_api.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private long id;
    private String productName;
    private Long categoryId;
    private Long quantity;
    private Long price;
    private Long status;
    private String image;

}
