package com.example.eShopping2.webApi.controller;

import com.example.eShopping2.business.abstracts.CartService;
import com.example.eShopping2.business.request.CreateCartItemRequest;
import com.example.eShopping2.business.response.GetAllCartItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    @PostMapping("/{userId}/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addItemToCart(@PathVariable int userId, CreateCartItemRequest createCartItemRequest){
        this.cartService.addItemToCart(userId,createCartItemRequest);
    }
    @DeleteMapping("/{userId}/remove/{cartItemId}")
    public void removeItemFromCart(@PathVariable int userId,@PathVariable int cartItemId){
        this.cartService.removeItemFromCart(userId,cartItemId);
    }
    @GetMapping("/{userId}/itemsId")
    public List<GetAllCartItemResponse> getItemsUser(@PathVariable int userId){
        return cartService.getAllCartItems(userId);
    }
    @GetMapping("{userId}/totalPrice")
    public BigDecimal getTotalCartPrice(@PathVariable int userId){
        return this.cartService.getTotalCartPrice(userId);
    }
    @PutMapping("/{userId}/update/{cartItemId}/{quantity}")
    public void update(@PathVariable int userId,@PathVariable int cartItemId,@PathVariable int quantity){
        this.cartService.update(userId,cartItemId,quantity);
    }
}
