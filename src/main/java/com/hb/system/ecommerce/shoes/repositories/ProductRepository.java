package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hb.system.ecommerce.shoes.entity.Product;
>>>>>>> a6f3e0b2c1c02c85c3d30d6b0ab5fbf32a4276a4

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProNameContaining(String proName);
    //Optional<Product> findById(int id);
}
