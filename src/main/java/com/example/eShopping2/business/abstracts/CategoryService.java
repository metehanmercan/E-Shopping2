package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateCategoriesRequest;
import com.example.eShopping2.business.request.UpdateCategoriesRequest;

import com.example.eShopping2.business.response.GetAllCategoriesResponse;
import com.example.eShopping2.business.response.GetByIdCategoriesResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoriesResponse> getAll();
    GetByIdCategoriesResponse geyById(int id);

   List<GetAllCategoriesResponse>  getKeyword(String name);

    void add(CreateCategoriesRequest createCategoriesRequest);
    void update(UpdateCategoriesRequest updateCategoriesRequest);
    void delete(int id);

}
