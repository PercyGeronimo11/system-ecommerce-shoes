package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.system.ecommerce.shoes.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer>{
    Material findById(int id);

    @Query("SELECT m FROM Material m WHERE m.status = true")
    List<Material> findAllActiveMaterials();

}
