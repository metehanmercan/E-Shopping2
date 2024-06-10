package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartItemRequest {
    private int productId;
    private int quantity;
}
