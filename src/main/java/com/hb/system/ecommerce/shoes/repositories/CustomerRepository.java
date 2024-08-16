package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;
import java.util.Optional;

import com.hb.system.ecommerce.shoes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hb.system.ecommerce.shoes.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    Optional<Customer> findByCustDni(String dni);
    Optional<Customer> findByUsuario(User user);
    Optional<Customer> findByCustEmail(String custEmail);
    @Query("SELECT c FROM Customer c WHERE c.usuario.name = :name")
    List<Customer> findByName(@Param("name") String username);
    Optional<Customer> findByCustEmailAndCustPassword(String custEmail, String custPassword);
    Optional<Customer> findById(int id);
    List<Customer> findByCustStatus(boolean custStatus);
}
