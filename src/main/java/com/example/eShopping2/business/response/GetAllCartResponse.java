package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartResponse {
    private int id;
    private int userId;
    private List<GetAllCartItemResponse> cartItems;
    private BigDecimal totalPrice;

}
