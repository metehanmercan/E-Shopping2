package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
