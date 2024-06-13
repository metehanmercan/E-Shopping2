package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.OrderService;
import com.example.eShopping2.business.request.CreateOrderRequest;
import com.example.eShopping2.entity.Order;
import com.example.eShopping2.entity.User;
import com.example.eShopping2.enums.OrderStatus;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add( CreateOrderRequest createOrderRequest) throws IllegalAccessException, StripeException {
        this.orderService.add(createOrderRequest);
    }

    @PostMapping("/cansel")
    public void canselOrder(@RequestParam int orderId,@RequestParam int userId){
        this.orderService.canselOrder(orderId,userId);
    }

    @PutMapping("/updateOrderStatus")
    public void updat(int  orderId, OrderStatus status){
        this.orderService.updateOrderStatus(orderId,status);
    }
}
