package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.services.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/store")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try {
            categoryService.createCategory(category);
            String mensaje = "Category creado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            String mensajeError = "Error al crear el Category: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensajeError);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        try {
            categoryService.updateCategory(id, category);
            String mensaje = "Category actualizado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (IllegalArgumentException e) {
            String mensajeError = "Error al actualizar el Category: " + e.getMessage();
            return ResponseEntity.badRequest().body(mensajeError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteCategory(id);
            String mensaje = "Category eliminado exitosamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            String mensajeError = "Error al eliminar el Category con ID " + id;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
}
