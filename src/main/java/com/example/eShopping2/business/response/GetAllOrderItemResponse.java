package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllOrderItemResponse {
    private int productId;
    private String productName;
    private int quantity;
    private BigDecimal price;
}
