package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);
    Optional<User> findByEmail(String email);
}

