package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.concretes.PaymentManager;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PaymentController {
    private PaymentManager paymentManager;

    @PostMapping("/create-payment-intent")
    public PaymentIntent createPaymentIntent(@RequestParam BigDecimal amount) throws StripeException {
        return paymentManager.createPaymentIntent(amount);
    }
}
