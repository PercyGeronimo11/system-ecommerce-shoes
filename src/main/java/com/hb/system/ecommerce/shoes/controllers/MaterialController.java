package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.services.MaterialService;

@Controller
@RequestMapping("/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping({"/list"})
    public String getAllMaterials(Model model) {
        model.addAttribute("materials", materialService.getAllMaterials());
        model.addAttribute("contenido", "materials/MaterialList");
        return "layout/index";
    }

    @GetMapping({"/create"})
    public String showCreateForm(Material material, Model model) {
        model.addAttribute("material", material);
        model.addAttribute("contenido", "materials/MaterialCreate");
        return "layout/index";
    }

    @PostMapping({"/store"})
    public String saveMaterial(@ModelAttribute("material") Material material) {
        materialService.saveMaterial(material);
        return "redirect:/material/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Material material = materialService.getMaterialById(id);
        model.addAttribute("material", material);
        model.addAttribute("contenido", "materials/MaterialEdit");
        return "layout/index";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Integer id, Model model) {
        Material material = materialService.getMaterialById(id);
        model.addAttribute("material", material);
        return "confirm-delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteMaterial(@PathVariable("id") Integer id) {
        materialService.deleteMAterial(id);
        return "redirect:/material/list";
    }

    @PostMapping("update/{id}")
    public String UpdateMaterial(@PathVariable int id, @ModelAttribute Material material, Model model) {
        Material materialOld = materialService.getMaterialById(id);
        materialOld = material;
        materialOld.setId(id);
        materialService.saveMaterial(materialOld);
        return "redirect:/material/list";
    }
}
