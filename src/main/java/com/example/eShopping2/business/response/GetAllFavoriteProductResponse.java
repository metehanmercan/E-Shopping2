package com.example.eShopping2.business.response;

import com.example.eShopping2.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllFavoriteProductResponse {
    private int id;
    private int userId;
    private int productId;
}
