package com.example.eShopping2.business.concretes;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class PaymentManager {

   @Value("${stripe.api.key}")
    private String apiKey;

    @PostConstruct
    public void init(){
        Stripe.apiKey=apiKey;
    }
    public PaymentIntent createPaymentIntent(BigDecimal amount) throws StripeException {
        // Stripe miktarı en küçük para birimi (cent) cinsinden bekler
        Long amountInCents = amount.multiply(new BigDecimal(100)).longValue();

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amountInCents)
                        .setCurrency("try")
                        .build();

        return PaymentIntent.create(params);
    }
}
