package com.example.eShopping2.business.request;

import com.example.eShopping2.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> imageUrls; // Resim dosyasını burada tutacağız ----- list<string> url yap
    private int stockQuantity;
    private String colour;
    private int categoryId;
    private int brandId;
}

