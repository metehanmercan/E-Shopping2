package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.FavoriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct,Integer> {
    List<FavoriteProduct> findFavoriteProductsByUserId(int userId);
}
