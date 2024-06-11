package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateFavoriteProductRequest;
import com.example.eShopping2.business.request.UpdateFavoriteProductRequest;
import com.example.eShopping2.business.response.GetAllFavoriteProductResponse;

import java.util.List;

public interface FavoriteProductService {
    void add(CreateFavoriteProductRequest createFavoriteProduct);

    void update(UpdateFavoriteProductRequest updateFavoriteProductRequest);

    void delete(int id);

    List<GetAllFavoriteProductResponse> getAll();

    List<GetAllFavoriteProductResponse> getUserId(int userId);
}
