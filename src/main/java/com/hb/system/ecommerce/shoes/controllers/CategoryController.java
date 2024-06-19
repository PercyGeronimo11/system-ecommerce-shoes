package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping({"/list"})
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("contenido", "categories/CategoryList");
        return "layout/index";
    }

    @GetMapping({"/create"})
    public String getCreateCategory(Category categoria,Model model) {
        model.addAttribute("categoria", categoria);
        model.addAttribute("contenido", "categories/CategoryCreate");
        return "layout/index";
    }

    @PostMapping({"/store"})
    public String NewCategory(@ModelAttribute Category category) {
        category.setState(true);
        categoryRepository.save(category);
        return "redirect:/category/list";
    }
    
    @GetMapping("delete/{id}")
    public String eliminarGenero(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Category category = categoryRepository.findById(id);
        categoryRepository.delete(category);
        return "redirect:/category/list";
    }

    @GetMapping("edit/{id}")
    public String getEditCategory(@PathVariable int id, Model model) {
        Category category = categoryRepository.findById(id);
        model.addAttribute("category", category);
        model.addAttribute("contenido", "categories/CategoryEdit");
        return "layout/index";
    }

    @PostMapping("update/{id}")
    public String UpdateCategory(@PathVariable int id, @ModelAttribute Category category, Model model) {
        Category categoryOld = categoryRepository.findById(id);
        categoryOld = category;
        categoryOld.setId(id);
        categoryRepository.save(categoryOld);
        return "redirect:/category/list";
    }

}
