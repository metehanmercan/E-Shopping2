package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.CartService;
import com.example.eShopping2.business.request.CreateCartItemRequest;
import com.example.eShopping2.business.response.GetAllCartItemResponse;
import com.example.eShopping2.dataAccess.CartItemRepository;
import com.example.eShopping2.dataAccess.CartRepository;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.entity.Cart;
import com.example.eShopping2.entity.CartItem;
import com.example.eShopping2.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartManager implements CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    @Override
    public void addItemToCart(int userId, CreateCartItemRequest createCartItemRequest) {
        Cart cart = this.cartRepository.findByUserId(userId);
        Product product = this.productRepository.getById(createCartItemRequest.getProductId());

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(createCartItemRequest.getQuantity());
        cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(createCartItemRequest.getQuantity())));
        cartItemRepository.save(cartItem);

        cart.setTotalPrice(cart.getTotalPrice().add(cartItem.getPrice()));
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(int userId, int cartItemId) {
        Cart cart = this.cartRepository.findByUserId(userId);
        CartItem cartItem = this.cartItemRepository.getById(cartItemId);
        if (!cart.getCartItems().contains(cartItem)) {
            throw new RuntimeException("CartItem does not belong to the user's cart");
        }
        cart.setTotalPrice(cart.getTotalPrice().subtract(cartItem.getPrice()));
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public List<GetAllCartItemResponse> getAllCartItems(int userId) {
        List<CartItem> cartItems = cartItemRepository.findByCartUserId(userId);
        List<GetAllCartItemResponse> getAllCartItemResponses = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            GetAllCartItemResponse getAllCartItemResponse=new GetAllCartItemResponse();
            getAllCartItemResponse.setId(cartItem.getId());
            getAllCartItemResponse.setTotalPrice(cartItem.getPrice());
            getAllCartItemResponse.setQuantity(cartItem.getQuantity());
            getAllCartItemResponse.setProductName(cartItem.getProduct().getName());
            getAllCartItemResponse.setPrice(cartItem.getProduct().getPrice()); // bu product ın birim fiyatı
            getAllCartItemResponse.setProductId(cartItem.getProduct().getId());
            getAllCartItemResponses.add(getAllCartItemResponse);
        }
        return getAllCartItemResponses;
    }

    @Override
    public BigDecimal getTotalCartPrice(int userId) {
        Cart cart=this.cartRepository.findByUserId(userId);
        return cart.getTotalPrice();
    }

    @Override
    public void update(int userId, int cartItemId, int quantity) {
        Cart cart=this.cartRepository.findByUserId(userId);
        CartItem cartItem=this.cartItemRepository.getById(cartItemId);
        if(!cart.getCartItems().contains(cartItem)){
            throw new RuntimeException("CartItem does not belong to the user's cart");
        }

        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(quantity)));
        cartItemRepository.save(cartItem);


        BigDecimal newTotalPrice = BigDecimal.ZERO;
        for (CartItem item : cart.getCartItems()) {
            newTotalPrice = newTotalPrice.add(item.getPrice());
        }

        cart.setTotalPrice(newTotalPrice);
        cartRepository.save(cart);
    }
}
