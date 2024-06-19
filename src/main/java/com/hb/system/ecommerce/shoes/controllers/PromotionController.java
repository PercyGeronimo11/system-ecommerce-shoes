package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.repositories.PromotionRepository;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping({ "/list" })
    public String getAllPromotions(Model model) {
        model.addAttribute("promotions", promotionRepository.findAll());
        model.addAttribute("contenido", "promotions/PromotionList");
        return "layout/index";
    }

    @GetMapping({ "/create" })
    public String getCreatePromotion(Promotion promocion, Model model) {
        model.addAttribute("promotion", promocion);
        model.addAttribute("contenido", "promotions/PromotionCreate");
        return "layout/index";
    }

    @PostMapping({ "/new" })
    public String StoreCategory(@ModelAttribute Promotion promotion) {
        promotionRepository.save(promotion);
        return "redirect:/promotion/list";
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
        promotionOld.setPROM_id(id);
        promotionRepository.save(promotionOld);
        return "redirect:/promotion/list";
    }
}