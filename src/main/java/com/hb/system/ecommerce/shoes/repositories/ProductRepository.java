package com.hb.system.ecommerce.shoes.repositories;

import com.hb.system.ecommerce.shoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProNameContaining(String proName);
    //Optional<Product> findById(int id);
}
