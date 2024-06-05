package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
