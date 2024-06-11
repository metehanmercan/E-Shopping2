package com.example.eShopping2.business.response;

import com.example.eShopping2.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllOrderResponse {
    private int id;
    private int userId;
    private Date orderDate;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private List<GetAllOrderItemResponse> orderItems;
}
