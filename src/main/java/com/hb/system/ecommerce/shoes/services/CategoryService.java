package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
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
        Optional<Category> categoryFind=categoryRepository.findById(id);
        //if(categoryFind.isPresent())  aquí valida con excepciones
        return categoryFind.get();

    }

  public Category save(Category resource) {
      resource.setCatStatus(true);
      if (resource.getCatHasTaco() == null) {
          resource.setCatHasTaco(false);
      }
      resource.setCatHasTaco(true);
      return categoryRepository.save(resource);
}

   public Category update(int id, Category resource){
    if (categoryRepository.existsById(id)) {
        resource.setId(id);
        return categoryRepository.save(resource);
    } else
        return null;
}
// Método para eliminar (desactivar) una catg
public void delete(int id) {
    Optional<Category> categoria = categoryRepository.findById(id);
    categoria.get().setCatStatus(false);
    categoryRepository.save(categoria.get());

}
}
