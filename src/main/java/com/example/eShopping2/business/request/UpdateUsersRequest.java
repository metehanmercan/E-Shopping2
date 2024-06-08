package com.example.eShopping2.business.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsersRequest {
    private int id;
   @NotBlank
   @NotNull
   @Size
    private String email;
    @NotBlank
    @NotNull
    @Size
    private String password;

    @NotBlank
    @NotNull
    @Size(min = 5,max = 15)
    private String phone;

}
