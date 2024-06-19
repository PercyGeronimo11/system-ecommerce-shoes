package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer>{
    Material findById(int id);
}
