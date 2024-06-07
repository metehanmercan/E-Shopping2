package com.example.eShopping2.business.request;

import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    private String name;
    private String content;
    private User userId;
}
