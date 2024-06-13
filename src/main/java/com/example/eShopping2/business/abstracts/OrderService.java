package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateOrderRequest;
import com.example.eShopping2.enums.OrderStatus;
import com.stripe.exception.StripeException;

public interface OrderService {

    void add( CreateOrderRequest createOrderRequest) throws IllegalAccessException, StripeException;

    void canselOrder(int orderId,int userId);

    void updateOrderStatus(int orderId, OrderStatus status);
}
