package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Integer> {
  Category  findByName(String name);

  List<Category> findByNameContaining(String name);
}
