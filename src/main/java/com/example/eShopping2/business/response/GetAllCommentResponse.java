package com.example.eShopping2.business.response;

import com.example.eShopping2.entity.Product;
import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class GetAllCommentResponse {

    private  int id;
    private  String content;
    private LocalDateTime created;
    private int userId;
    private int productId;
}
