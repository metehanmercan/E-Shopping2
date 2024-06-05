package com.example.eShopping2.business.response;

import com.example.eShopping2.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetByIdUsersResponse {
    private int id;
    private String name;
    private String phone;
    private String fullName;
    private String email;
    private String password;
    private UserRole role;
}
