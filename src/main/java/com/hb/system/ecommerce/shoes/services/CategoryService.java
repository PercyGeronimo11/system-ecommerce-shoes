package com.hb.system.ecommerce.shoes.services;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public java.util.List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void updateCategory(Integer categoryId, Category category) {
        if (categoryRepository.existsById(categoryId)) {
            category.setId(categoryId);
            categoryRepository.save(category);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    public void deleteCategory(Integer categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new CategoryNotFoundException("Category not found with ID: " + categoryId);
        }
    }

    public class CategoryNotFoundException extends RuntimeException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }
}
