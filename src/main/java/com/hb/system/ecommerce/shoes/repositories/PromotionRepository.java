package com.hb.system.ecommerce.shoes.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
	Promotion findById(int id); //buscar una entidad por su ID en la bd.
}
