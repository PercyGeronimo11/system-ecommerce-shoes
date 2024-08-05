package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hb.system.ecommerce.shoes.entity.Role;
import com.hb.system.ecommerce.shoes.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findById(int id);
    Optional<User> findByUsername(String username);
    List<User> findUsersByStatus(boolean status);
    @Query("SELECT u FROM User u WHERE u.status = :status AND u.role IN :roles")
    List<User> findUsersByStatusAndRoles(@Param("status") boolean status, @Param("roles") List<Role> roles);
}

