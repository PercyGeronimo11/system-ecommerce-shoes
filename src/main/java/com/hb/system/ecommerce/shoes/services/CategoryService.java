package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
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
        return categoryRepository.findById(id);
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
    Category categoria = categoryRepository.findById(id);
    // Establece el estado de la catg a 'false' (inactiva)
    categoria.setCAT_status(false);
    // Guarda 
    categoryRepository.save(categoria);
}
}
