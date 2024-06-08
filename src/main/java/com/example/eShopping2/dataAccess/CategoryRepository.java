package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Category;

import com.example.eShopping2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
  Category  findByName(String name);

  List<Category> findByNameContaining(String name);


  // Kategoriye ait ürünleri getiren metod
  @Query("SELECT p FROM Product p WHERE p.category.name = :name")
  List<Product> findProductsByCategoryName(@Param("name") String name);
}
