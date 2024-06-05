package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
