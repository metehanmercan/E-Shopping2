package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateCartItemRequest;
import com.example.eShopping2.business.response.GetAllCartItemResponse;
import com.example.eShopping2.entity.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addItemToCart(int userId, CreateCartItemRequest createCartItemRequest);
    void removeItemFromCart(int userId,int cartItemId);
    List<GetAllCartItemResponse> getAllCartItems(int userId);
    BigDecimal getTotalCartPrice(int userId);
    void update(int userId,int cartItemId,int quantity);

    void clearCart(Cart cart);
}
