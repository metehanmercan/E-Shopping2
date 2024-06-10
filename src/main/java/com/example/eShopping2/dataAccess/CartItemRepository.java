package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByCartUserId(int userId);
}
