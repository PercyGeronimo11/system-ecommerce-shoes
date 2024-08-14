package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.*;

public interface PromotionDetailRepository extends JpaRepository<PromotionDetail, Integer>{
    
    Optional<PromotionDetail> findById(int id);
    List<PromotionDetail> findByDetStatus(boolean detStatus);
    List<PromotionDetail> findByPromotionId(int promotionId);

    PromotionDetail findByProductId(int productId);


    List<PromotionDetail> findByPromotionIdAndDetStatus(int promotionId, boolean detStatus);


    void deleteById(int idDetalle);
    List<PromotionDetail> findByPromotion(Promotion promotion);
    List<PromotionDetail> deleteAllByPromotion(Promotion promotion);
    List<PromotionDetail> findAllByPromotion(Promotion promotion);

}
