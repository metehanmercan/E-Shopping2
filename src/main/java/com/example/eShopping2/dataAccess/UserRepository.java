package com.example.eShopping2.dataAccess;

import com.example.eShopping2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByNameContaining(String name);

    boolean existsByName(String name);
    boolean existsById(int id);
    boolean existsByEmail(String email);
}
