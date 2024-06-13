package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.entity.Order;
import com.example.eShopping2.entity.User;

public interface NotificationService {

    void sendOrderConfirmation(User user, Order order);
     void sendOrderConfirmationInvoice(User user, Order order);
    void sendOrderCancellationNotification(User user, Order order);
    void sendOrderStatusUpdateNotification(User user, Order order);
}
