package com.example.eShopping2.business.request;

import com.example.eShopping2.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private MultipartFile imageFile; // Resim dosyasını burada tutacağız
    private int stockQuantity;
    private String colour;
    private int categoryId;
    private int brandId;


}
