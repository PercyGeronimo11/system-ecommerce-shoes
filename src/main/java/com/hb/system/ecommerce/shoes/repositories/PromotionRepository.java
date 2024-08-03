package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hb.system.ecommerce.shoes.entity.Promotion;


public interface PromotionRepository extends JpaRepository<Promotion, Integer>{

    List<Promotion> findByPromStatus(boolean promStatus);
    Optional<Promotion> findById(int idpromocion);

}
