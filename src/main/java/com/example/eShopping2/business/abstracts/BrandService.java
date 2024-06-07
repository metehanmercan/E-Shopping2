package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateBrandRequest;
import com.example.eShopping2.business.request.UpdateBrandRequest;

import com.example.eShopping2.business.response.GetAllBrandsResponse;

import java.util.List;

public interface BrandService {
    List<GetAllBrandsResponse> getKeyword(String keyword);
    void add(CreateBrandRequest createBrandRequest);
    List<GetAllBrandsResponse> getAll();

    GetAllBrandsResponse getById(int id);

    void update(UpdateBrandRequest updateBrandRequest);
    void  delete(int id);


}
