package com.example.eShopping2.business.concretes;

import com.example.eShopping2.business.abstracts.InventoryService;
import com.example.eShopping2.dataAccess.ProductRepository;
import com.example.eShopping2.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryManager implements InventoryService {
   private ProductRepository productRepository;

    @Override
    public void update(Product product) {
        this.productRepository.save(product);
    }

    @Override
    public void checkStock(Product product, int quantity) {
        if (product.getStockQuantity()<quantity){
            throw new IllegalStateException("Product out of stock: " + product.getName());

        }
    }
}
