package com.hb.system.ecommerce.shoes.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    // Inyección de dependencias de PromotionRepository
    @Autowired
    private PromotionRepository promotionRepository;
    
    // Método para listar todas las promociones activas
    public List<Promotion> listAll() {
        // Llama al método del repositorio que devuelve todas las promociones activas
        return promotionRepository.findAllActivePromotions();
    }

    // Método para obtener una promoción por su ID
    public Promotion getById(int id) {
        // Llama al método del repositorio que busca una promoción por su ID
        return promotionRepository.findById(id);
    }

    // Método para guardar una nueva promoción
    public Promotion save(Promotion resource) {
        // Establece el estado de la promoción a 'true' (activa)
        resource.setPROMT_status(true);
        // Guarda la promoción en el repositorio y devuelve el objeto guardado
        return promotionRepository.save(resource);
    }

    // Método para actualizar una promoción existente
    public Promotion update(int id, Promotion resource){
        // Comprueba si una promoción con el ID dado existe
        if (promotionRepository.existsById(id)) {
            // Establece el ID de la promoción al ID dado
            resource.setPROMT_id(id);
            // Guarda la promoción actualizada en el repositorio y devuelve el objeto guardado
            return promotionRepository.save(resource);
        } else
            // Si la promoción no existe, devuelve null
            return null;
    }

    // Método para eliminar (desactivar) una promoción
    public void delete(int id) {
        // Busca la promoción por su ID
        Promotion promocion = promotionRepository.findById(id);
        // Establece el estado de la promoción a 'false' (inactiva)
        promocion.setPROMT_status(false);
        // Guarda la promoción con el estado actualizado en el repositorio
        promotionRepository.save(promocion);
    }
}
