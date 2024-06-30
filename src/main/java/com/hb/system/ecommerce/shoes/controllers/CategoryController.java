package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.services.CategoryService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category categoryCreate = categoryService.createCategory(category);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryCreate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("nada")
    public java.util.List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
    @PostMapping("/demo")
    public String welcome()
    {
        return "Welcome from secure endpoint";
    }
    
}
