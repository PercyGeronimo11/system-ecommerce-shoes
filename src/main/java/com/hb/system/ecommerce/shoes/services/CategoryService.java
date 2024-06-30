package com.hb.system.ecommerce.shoes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        Category category = categoryRepository.findById(id);
    
        if (category != null) {
            return category;
        } else {
            throw new GeneroNotFoundException("Categoría no encontrado");
        }
    }

    public void updateCategoría(int idCategory, Category category) {
        if (categoryRepository.existsById(idCategory)) {
            category.setId(idCategory);
            categoryRepository.save(category);
        } else {
            throw new GeneroNotFoundException("Categoría no encontrado con id: " + idCategory);
        }
    }

    public void deleteCategory(int idCategory) {
        if (categoryRepository.existsById(idCategory)) {
            categoryRepository.deleteById(idCategory);
        } else {
            throw new GeneroNotFoundException("Categoría no encontrado con id: " + idCategory);
        }
    }

    public class GeneroNotFoundException extends RuntimeException {
        public GeneroNotFoundException(String message) {
            super(message);
        }
    }
}
