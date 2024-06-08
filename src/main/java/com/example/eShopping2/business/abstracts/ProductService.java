package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateProductRequest;
import com.example.eShopping2.business.request.UpdateProductRequest;
import com.example.eShopping2.business.response.GetAllProductResponse;
import com.example.eShopping2.business.response.GetByIdProductResponse;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void add(CreateProductRequest createProductRequest);
    List<GetAllProductResponse> getAll();
    void update(UpdateProductRequest updateProductRequest);

    GetByIdProductResponse getById(int id);
    void delete(int id);

    List<GetAllProductResponse> getKeyword(String keyword);
}
