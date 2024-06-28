package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.services.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
    
    @GetMapping("/list")
    public List<Promotion> getPromotions() {
        return promotionService.getAllPromotions();
    }

    @PostMapping("/store")
    public ResponseEntity<String> createPromotion(@RequestBody Promotion promotion) {
        try {
            // Validar los datos manualmente
            if (promotion.getPROMT_start_date() == null || promotion.getPROMT_end_date() == null) {
                throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
            }

            promotionService.createPromotion(promotion);
            String mensaje = "Promotion creado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            String mensajeError = "Error al crear el Promotion: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensajeError);
        } catch (Exception e) {
            String mensajeError = "Error interno al crear el Promotion.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePromotion(@PathVariable Integer id, @RequestBody Promotion promotion) {
        try {
            // Validar los datos manualmente
            if (promotion.getPROMT_start_date() == null || promotion.getPROMT_end_date() == null) {
                throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
            }

            promotionService.updatePromotion(id, promotion);
            String mensaje = "Promotion actualizado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            String mensajeError = "Error al actualizar el Promotion: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensajeError);
        } catch (Exception e) {
            String mensajeError = "Error interno al actualizar el Promotion con ID " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePromotion(@PathVariable Integer id) {
        try {
            promotionService.deletePromotion(id);
            String mensaje = "Promotion eliminado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            String mensajeError = "Error al eliminar el Promotion con ID " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
}
