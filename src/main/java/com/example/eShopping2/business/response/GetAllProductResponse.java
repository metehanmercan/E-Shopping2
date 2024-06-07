package com.example.eShopping2.business.response;

import com.example.eShopping2.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllProductResponse {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private int stockQuantity;
    private String colour;
    private String categoryName;
    private String brandName;
}
