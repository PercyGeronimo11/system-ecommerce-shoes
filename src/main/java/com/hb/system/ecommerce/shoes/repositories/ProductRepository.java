package com.hb.system.ecommerce.shoes.repositories;

import com.hb.system.ecommerce.shoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import java.util.List;

=======

import java.util.List;
>>>>>>> a6f3e0b2c1c02c85c3d30d6b0ab5fbf32a4276a4

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProNameContaining(String proName);
    //Optional<Product> findById(int id);
}
