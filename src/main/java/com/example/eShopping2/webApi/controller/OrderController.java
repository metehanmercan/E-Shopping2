package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.OrderService;
import com.example.eShopping2.business.request.CreateOrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    @PostMapping("/add")
    public void add( CreateOrderRequest createOrderRequest) throws IllegalAccessException {
        this.orderService.add(createOrderRequest);
    }
}
