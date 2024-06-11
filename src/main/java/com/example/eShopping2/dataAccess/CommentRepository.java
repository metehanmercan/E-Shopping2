package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findCommentsByProductId(int producId);
}
