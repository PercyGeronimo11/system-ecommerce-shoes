package com.hb.system.ecommerce.shoes.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.services.PromotionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/promotion")
@RequiredArgsConstructor
public class PromotionController {
    @Autowired
    private final PromotionService promotionService;

    @GetMapping({ "/list" })
    public java.util.List<Promotion> getPromotions() {
        return promotionService.getAllPromotions();
        
    }

    @PostMapping({ "/store" })
    public ResponseEntity<String> createPromotion(@RequestBody Promotion promotion) {
        try {
            promotionService.createPromotion(promotion);
            String mensaje = "Promotion creado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            String mensajeError = "Error al crear el Promotion.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    @PatchMapping("/{id}")
    public void UpdatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion) {
        promotionService.updatePromotion(id, promotion);
    }

    @DeleteMapping("/{id}")
    public void deletePromotion(@PathVariable Integer id) {
        promotionService.deletePromotion(id);
    }
}