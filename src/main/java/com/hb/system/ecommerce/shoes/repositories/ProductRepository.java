package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
}
