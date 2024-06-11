package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CartService;
import com.example.eShopping2.business.abstracts.InventoryService;
import com.example.eShopping2.business.abstracts.OrderService;
import com.example.eShopping2.business.request.CreateOrderRequest;
import com.example.eShopping2.dataAccess.OrderRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.*;
import com.example.eShopping2.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private InventoryService inventoryService;
    private CartService cartService;

    @Transactional
    @Override
    public void add( CreateOrderRequest createOrderRequest) throws IllegalAccessException {
        User user = this.userRepository.findById(createOrderRequest.getUserId()).orElseThrow();

        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            throw new IllegalAccessException("cart is empty");
        }

        // Stok kontrolü ve güncelleme
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            inventoryService.checkStock(product, cartItem.getQuantity());
            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            inventoryService.update(product);
        }

        // ödeme işlemi araştır
      //  paymentService.processPayment(user, totalPrice);


        // sipariş oluşturma
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(cart.getTotalPrice());

       // OrderItem oluşturma ve siparişe ekleme
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem:cart.getCartItems()) {
        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPrice(cartItem.getPrice());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProduct(cartItem.getProduct());
        orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);

        // Bildirim gönderme araştır
    //    notificationService.sendOrderConfirmation(user, order);


        // sepeti boşaltma
        cartService.clearCart(cart);
    }
}

