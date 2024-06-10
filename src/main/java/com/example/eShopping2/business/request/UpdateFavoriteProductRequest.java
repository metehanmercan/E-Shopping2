package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateFavoriteProductRequest {
    private int id;
    @NotNull
    private int userId;

    @NotNull
    private int productId;
}
