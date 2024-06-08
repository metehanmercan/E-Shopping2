package com.example.eShopping2.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAddressResponse {
    private int id;
    private String street;
    private String city;
    private String neighborhood;
    private int userId;
}
