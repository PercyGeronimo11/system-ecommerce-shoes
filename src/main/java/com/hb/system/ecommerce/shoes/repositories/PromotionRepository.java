package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hb.system.ecommerce.shoes.entity.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
    Promotion findById(int id);

    @Query("SELECT p FROM Promotion p WHERE p.PROMT_status = true")
    List<Promotion> findAllActivePromotions();
}
