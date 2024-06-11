package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateOrderRequest;

public interface OrderService {

    void add( CreateOrderRequest createOrderRequest) throws IllegalAccessException;
}
