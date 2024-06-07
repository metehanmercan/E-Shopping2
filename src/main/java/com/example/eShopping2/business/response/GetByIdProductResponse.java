package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdProductResponse {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int stockQuantity;
    private String colour;
    private String categoryName;
    private String brandName;
}
