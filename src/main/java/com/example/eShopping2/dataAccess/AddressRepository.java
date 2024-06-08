package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findAddressByUserId(int userId);
}
