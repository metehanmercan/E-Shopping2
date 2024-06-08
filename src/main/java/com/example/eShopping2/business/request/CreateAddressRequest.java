package com.example.eShopping2.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {

    private String street;
    private String city;
    private String neighborhood;
    private int userId;
}
