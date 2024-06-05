package com.example.eShopping2.business.request;
import com.example.eShopping2.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUsersRequest {
    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String fullName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25)
    private String phone;

    @NotNull
    private UserRole role;
}
