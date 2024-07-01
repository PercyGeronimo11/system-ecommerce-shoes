package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.system.ecommerce.shoes.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);
    Optional<User> findByUsername(String username); 

    @Query("SELECT m FROM User m WHERE m.status = true")
    List<User> findAllActiveUsers();
}

