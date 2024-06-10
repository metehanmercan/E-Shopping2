package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUserId(int userId);
}
