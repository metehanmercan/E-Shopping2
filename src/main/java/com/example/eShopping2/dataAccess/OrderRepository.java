package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
