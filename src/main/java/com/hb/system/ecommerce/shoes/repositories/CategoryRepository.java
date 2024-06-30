package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
