package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    // Inyección de dependencias de PromotionRepository
    @Autowired
    private  CategoryRepository categoryRepository;

    // Método para listar todas las catg activas
    public List<Category> listAll() {
        // Llama al método del repositorio que devuelve todas las promociones activas
        return categoryRepository.findAllActiveCategories();
    }
    // Método para obtener una categ por su ID
    public Category getById(int id) {
        // Llama al método del repositorio que busca una catg por su ID
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }
  // Método para guardar una nueva categ
  public Category save(Category resource) {
    resource.setCAT_status(true);
    return categoryRepository.save(resource);
}
   // Método para actualizar una  catg existente
   public Category update(int id, Category resource){
    if (categoryRepository.existsById(id)) {
        resource.setCAT_id(id);
        return categoryRepository.save(resource);
    } else
        return null;
}
// Método para eliminar (desactivar) una catg
public void delete(int id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isPresent()) {
        Category categoria = optionalCategory.get();
        // Establece el estado de la categoría a 'false' (inactiva)
        categoria.setCAT_status(false);
        // Guarda la categoría actualizada
        categoryRepository.save(categoria);
    } else {
        throw new EntityNotFoundException("Category not found with id: " + id);
    }
}
}
