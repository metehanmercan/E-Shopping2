package com.example.eShopping2.dataAccess;

import com.example.eShopping2.business.response.GetAllCommentResponse;
import com.example.eShopping2.entity.Comment;
import com.example.eShopping2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByNameContaining(String keyword);

    List<GetAllCommentResponse> findByComments(Product product);
}
