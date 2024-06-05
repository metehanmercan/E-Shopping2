package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateProductRequest;

import java.io.IOException;

public interface ProductService {
    void add(CreateProductRequest createProductRequest) throws IOException;
}
