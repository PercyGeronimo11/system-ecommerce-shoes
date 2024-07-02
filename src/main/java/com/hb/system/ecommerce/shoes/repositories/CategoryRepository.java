package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.system.ecommerce.shoes.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

    Category findById(int id);

    @Query("SELECT c FROM Category c WHERE c.CAT_status = true")
    List<Category> findAllActiveCategories();
}
