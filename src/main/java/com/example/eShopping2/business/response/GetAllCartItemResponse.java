package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartItemResponse {
    private int id;
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;
}
