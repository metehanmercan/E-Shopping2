package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoriesRequest {

    private String currentName; // Güncellenecek kategorinin mevcut adı
    private String newName; // Yeni ad
}
