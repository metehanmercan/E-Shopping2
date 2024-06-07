package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    List<Brand> findByNameContaining(String keyword);
}
