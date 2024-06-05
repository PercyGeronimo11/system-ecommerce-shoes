package com.hb.system.ecommerce.shoes.repository;

import com.hb.system.ecommerce.shoes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAll();

}
