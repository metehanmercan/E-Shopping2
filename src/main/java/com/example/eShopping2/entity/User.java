package com.example.eShopping2.entity;

import com.example.eShopping2.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "userName")
    private String UserName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="userRole")
    private UserRole userRole;

    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<FavoriteProduct> favoriteProducts;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

}
