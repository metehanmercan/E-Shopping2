package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteProductRequest {

    @NotNull
    private int userId;

    @NotNull
    private int productId;
}
