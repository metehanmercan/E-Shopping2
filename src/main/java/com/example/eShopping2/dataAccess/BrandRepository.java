package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Brand;
import com.example.eShopping2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByNameContaining(String keyword);

    @Query("SELECT b.products FROM Brand b WHERE b.name = :name")
    List<Product> findProductsByBrandName(@Param("name") String name); // Bu sorgu, verilen marka adına göre ürünleri getirir

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")    // Bu sorgu, verilen marka  id'sine  göre ürünleri getirir
    List<Product> findProductsByBrandId(@Param("brandId") int brandId);


}
