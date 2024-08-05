package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.PromotionDetail;

public interface PromotionDetailRepository extends JpaRepository<PromotionDetail, Integer>{

    Optional<PromotionDetail> findById(int id);
    List<PromotionDetail> findByDetStatus(boolean detStatus);
    List<PromotionDetail> findByPromotionId(int promotionId);
    List<PromotionDetail> findByPromotionIdAndDetStatus(int promotionId, boolean detStatus);
}
