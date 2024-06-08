package com.example.eShopping2.business.response;

import com.example.eShopping2.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetByIdProductResponse {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> imageUrls;
    private int stockQuantity;
    private String colour;
    private String categoryName;
    private String brandName;
}
