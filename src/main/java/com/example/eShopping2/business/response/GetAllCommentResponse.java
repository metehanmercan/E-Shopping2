package com.example.eShopping2.business.response;

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
