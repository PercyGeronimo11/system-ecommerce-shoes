package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findById(int id);
}
