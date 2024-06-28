package com.hb.system.ecommerce.shoes.services;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private PromotionRepository promotionRepository;

    public void createPromotion(Promotion promotion) {

        promotionRepository.save(promotion);
    }

    public java.util.List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public void updatePromotion(Integer promotionId, Promotion promotion) {
        if (promotionRepository.existsById(promotionId)) {
            promotion.setPROMT_id(promotionId);
            promotionRepository.save(promotion);
        } else {
            throw new PromotionNotFoundException("Promotion not found with ID: " + promotionId);
        }
    }

    public void deletePromotion(Integer promotionId) {
        if (promotionRepository.existsById(promotionId)) {
            promotionRepository.deleteById(promotionId);
        } else {
            throw new PromotionNotFoundException("Promotion not found with ID: " + promotionId);
        }
    }

    public class PromotionNotFoundException extends RuntimeException {
        public PromotionNotFoundException(String message) {
            super(message);
        }
    }

}
