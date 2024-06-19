package com.hb.system.ecommerce.shoes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.repositories.MaterialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;
    
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(int id) {
        return materialRepository.findById(id);
    }

    public Material saveMaterial(Material material) {
        material.setStatus(true);
        return materialRepository.save(material);
    }

    public void deleteMAterial(Integer id) {
        materialRepository.deleteById(id);
    }
}
