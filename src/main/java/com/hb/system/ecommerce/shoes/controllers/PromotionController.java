package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.services.PromotionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
    @Autowired
    private final PromotionService promotionService;

    @GetMapping({ "/list" })
    public java.util.List<Promotion> getPromotions() {
        return promotionService.getAllPromotions();
    }

    @GetMapping({ "/create" })
    public String getCreatePromotion(Promotion promocion, Model model) {
        model.addAttribute("promotion", promocion);
        model.addAttribute("contenido", "promotions/PromotionCreate");
        return "layout/index";
    }

    @PostMapping({ "/new" })
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




    @GetMapping("delete/{id}")
    public String eliminarPromotion(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Promotion promotion = promotionRepository.findById(id);
        promotionRepository.delete(promotion);
        return "redirect:/promotion/list";
    }

    @GetMapping("edit/{id}")
    public String getEditPromotion(@PathVariable int id, Model model) {
        Promotion promotion = promotionRepository.findById(id);
        model.addAttribute("promotion", promotion);
        model.addAttribute("contenido", "promotions/PromotionEdit");
        return "layout/index";
    }

    @PostMapping("update/{id}")
    public String UpdatePromotion(@PathVariable int id, @ModelAttribute Promotion promotion, Model model) {
        Promotion promotionOld = promotionRepository.findById(id);
        promotionOld = promotion;
        promotionOld.setPROMT_id(id);
        promotionRepository.save(promotionOld);
        return "redirect:/promotion/list";
    }
    @GetMapping("/cancel")
    public String cancel(){
        return "redirect:/promotion/list";
    }
}