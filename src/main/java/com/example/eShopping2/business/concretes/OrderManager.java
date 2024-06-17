package com.example.eShopping2.business.concretes;

import com.example.eShopping2.InvoiceGenerator;
import com.example.eShopping2.business.abstracts.CartService;
import com.example.eShopping2.business.abstracts.InventoryService;
import com.example.eShopping2.business.abstracts.NotificationService;
import com.example.eShopping2.business.abstracts.OrderService;
import com.example.eShopping2.business.exception.BusinessException;
import com.example.eShopping2.business.request.CreateOrderRequest;
import com.example.eShopping2.dataAccess.AddressRepository;
import com.example.eShopping2.dataAccess.OrderRepository;
import com.example.eShopping2.dataAccess.UserRepository;
import com.example.eShopping2.entity.*;
import com.example.eShopping2.enums.OrderStatus;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class OrderManager implements OrderService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private InventoryService inventoryService;
    private CartService cartService;
    private NotificationService notificationService;
    private AddressRepository addressRepository;
    private InvoiceGenerator invoiceGenerator;
    private PaymentManager paymentManager;

    @Transactional
    @Override
    public void add(CreateOrderRequest createOrderRequest) throws IllegalAccessException, StripeException {
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

     /*   BigDecimal totalPrice = cart.getTotalPrice();
        PaymentIntent paymentIntent = paymentManager.createPaymentIntent(totalPrice);

        if ("succeeded".equals(paymentIntent.getStatus())) {
        } else {
            throw new IllegalAccessException("Payment failed");
        }
    }*/

        // sipariş oluşturma
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(cart.getTotalPrice());

        // OrderItem oluşturma ve siparişe ekleme
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        // Sipariş adresi oluştur
        Address address = new Address();
        address.setStreet(createOrderRequest.getStreet());
        address.setCity(createOrderRequest.getCity());
        address.setNeighborhood(createOrderRequest.getNeighborhood());
        address.setUser(user);
        addressRepository.save(address);

        this.orderRepository.save(order);


        // Mail ile Bildirim gönderme araştır
        //   notificationService.sendOrderConfirmation(user, order);

        // fatura detayı gönderme pdf olarak
        //   byte[] invoice = invoiceGenerator.generateInvoice(order);
        //    saveInvoiceToFileSystem(invoice, order.getId()); // PDF'yi dosya sistemi veya veritabanına kaydedebilirsiniz
        //    notificationService.sendOrderConfirmationInvoice(user,order);

        // sepeti boşaltma
        cartService.clearCart(cart);

    }

    private void saveInvoiceToFileSystem(byte[] invoice, int orderId) {
        // Fatura dosyasını dosya sistemi veya veritabanına kaydetme işlemi yapılabilir
        // Bu örnek üzerinde detaya girmiyorum, genel olarak işlemi gösteriyorum
        // Implementasyon size ve projenizin gereksinimlerine göre değişecektir
        // Örneğin, dosya sistemi kaydedebilir veya veritabanına BLOB olarak saklayabilirsiniz
    }

    @Override
    public void canselOrder(int orderId, int userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        if (!Objects.equals(order.getUser().getId(), userId)) {
            throw new RuntimeException("Unauthorized access");
        }


        // Ödeme iadesi işlemi ekle

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);

        // Bildirim gönderme
        //   notificationService.sendOrderCancellationNotification(order.getUser(),order);
    }

    @Override
    public void updateOrderStatus(int orderId, OrderStatus status) {
        Order order = this.orderRepository.findById(orderId).orElseThrow(() -> new BusinessException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);

        //bildiirim
        // notificationService.sendOrderStatusUpdateNotification(order.getUser(),order);
    }
}

