package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.entity.Product;

public interface InventoryService {
    void update(Product product);
    void checkStock(Product product,int quantity);
}
