package com.example.eShopping2.business.request;

import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {


    private String content;
    private int userId;
    private LocalDateTime createdAt;
    private int productId;
}
