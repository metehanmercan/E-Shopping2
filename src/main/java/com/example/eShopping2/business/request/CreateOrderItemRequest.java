package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {
    private int productId;
    private int quantity;
    private BigDecimal price;
}
