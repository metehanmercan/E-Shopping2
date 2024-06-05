package com.example.eShopping2.business.abstracts;

import com.example.eShopping2.business.request.CreateUsersRequest;
import com.example.eShopping2.business.request.UpdateUsersRequest;
import com.example.eShopping2.business.response.GetAllUsersResponse;
import com.example.eShopping2.business.response.GetByIdUsersResponse;
import com.example.eShopping2.entity.User;

import java.util.List;

public interface UserService {
    List<GetAllUsersResponse> getAll();
    List<GetAllUsersResponse> getKeyword(String keyword);
    GetByIdUsersResponse getById(int id);
    void add(CreateUsersRequest createUsersRequest);
    void update(UpdateUsersRequest updateUsersRequest);
    void delete(int id);
}
