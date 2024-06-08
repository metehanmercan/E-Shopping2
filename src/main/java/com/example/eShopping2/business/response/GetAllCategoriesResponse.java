package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCategoriesResponse {

    private int id;
    private String name;
    private List<GetAllCategoriesResponse> subCategories;


}
