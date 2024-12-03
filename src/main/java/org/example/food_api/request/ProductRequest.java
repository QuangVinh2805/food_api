package org.example.food_api.request;


import lombok.Getter;
import lombok.Setter;
import org.example.food_api.models.Category;

@Getter
@Setter
public class ProductRequest {
    private long id;
    private String productName;
    private Long categoryId;
    private Long quantity;
    private Long price;
    private String image;

}
