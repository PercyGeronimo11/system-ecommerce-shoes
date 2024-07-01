package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hb.system.ecommerce.shoes.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProNameContaining(String proName);
    //Optional<Product> findById(int id);

}
