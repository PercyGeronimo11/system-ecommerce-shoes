package com.hb.system.ecommerce.shoes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.repositories.MaterialRepository;
import com.hb.system.ecommerce.shoes.services.MaterialService;

@ExtendWith(MockitoExtension.class)
public class MaterialServiceTest {
    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @Test
    public void testSave() {
        Material material = new Material();
        material.setName("Cuero");
        material.setPrice(20.0);
        material.setQuantity(2);
        material.setUnit("metros");
        material.setStatus(false);

        Material esperado = new Material();
        esperado.setName("Cuero");
        esperado.setPrice(20.0);
        esperado.setQuantity(2);
        esperado.setUnit("metros");
        esperado.setStatus(true);

        Mockito.when(materialRepository.save(material)).thenReturn(esperado);

        Material result = materialService.save(material);

        assertNotNull(result);
        assertEquals("Cuero", result.getName());
        assertEquals(20.0, result.getPrice());
        assertEquals(2, result.getQuantity());
        assertEquals("metros", result.getUnit());
        assertEquals(true, result.getStatus());
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    public void testListAll() {
        Material material1 = new Material();
        material1.setName("Cuero");
        material1.setPrice(20.0);
        material1.setQuantity(2);
        material1.setUnit("metros");
        material1.setStatus(true);

        Material material2 = new Material();
        material2.setName("Tela");
        material2.setPrice(15.0);
        material2.setQuantity(5);
        material2.setUnit("metros");
        material2.setStatus(true);

        when(materialRepository.findMaterialsByStatus(true)).thenReturn(Arrays.asList(material1, material2));

        List<Material> result = materialService.listAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Cuero", result.get(0).getName());
        assertEquals("Tela", result.get(1).getName());
        verify(materialRepository, times(1)).findMaterialsByStatus(true);
    }

    @Test
    public void testGetById() {
        Material material = new Material();
        material.setName("Cuero");
        material.setPrice(20.0);
        material.setQuantity(2);
        material.setUnit("metros");
        material.setStatus(true);

        when(materialRepository.findById(1)).thenReturn(Optional.of(material));

        Material result = materialService.getById(1);

        assertNotNull(result);
        assertEquals("Cuero", result.getName());
        verify(materialRepository, times(1)).findById(1);
    }

    @Test
    public void testGetByIdNotFound() {
        when(materialRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            materialService.getById(1);
        });

        assertEquals("No se encontro material", thrown.getMessage());
        verify(materialRepository, times(1)).findById(1);
    }

    @Test
    public void testUpdate() {
        Material existingMaterial = new Material();
        existingMaterial.setId(1);
        existingMaterial.setName("Cuero");
        existingMaterial.setPrice(20.0);
        existingMaterial.setQuantity(2);
        existingMaterial.setUnit("metros");
        existingMaterial.setStatus(true);

        Material updatedMaterial = new Material();
        updatedMaterial.setName("Tela");
        updatedMaterial.setPrice(15.0);
        updatedMaterial.setQuantity(5);
        updatedMaterial.setUnit("metros");
        updatedMaterial.setStatus(true);

        when(materialRepository.existsById(1)).thenReturn(true);
        when(materialRepository.save(updatedMaterial)).thenReturn(updatedMaterial);

        Material result = materialService.update(1, updatedMaterial);

        assertNotNull(result);
        assertEquals("Tela", result.getName());
        assertEquals(15.0, result.getPrice());
        assertEquals(5, result.getQuantity());
        assertEquals("metros", result.getUnit());
        assertEquals(true, result.getStatus());
        verify(materialRepository, times(1)).save(updatedMaterial);
    }

    @Test
    public void testUpdateNotFound() {
        Material updatedMaterial = new Material();
        updatedMaterial.setName("Tela");
        updatedMaterial.setPrice(15.0);
        updatedMaterial.setQuantity(5);
        updatedMaterial.setUnit("metros");
        updatedMaterial.setStatus(true);

        when(materialRepository.existsById(1)).thenReturn(false);

        Material result = materialService.update(1, updatedMaterial);

        assertNull(result);
        verify(materialRepository, times(0)).save(updatedMaterial);
    }

    @Test
    public void testDelete() {
        Material material = new Material();
        material.setId(1);
        material.setName("Cuero");
        material.setPrice(20.0);
        material.setQuantity(2);
        material.setUnit("metros");
        material.setStatus(true);

        when(materialRepository.findById(1)).thenReturn(Optional.of(material));

        materialService.delete(1);

        verify(materialRepository, times(1)).save(material);
        assertEquals(false, material.getStatus());
    }

    @Test
    public void testDeleteNotFound() {
        when(materialRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            materialService.delete(1);
        });

        assertEquals("No se encontro material", thrown.getMessage());
        verify(materialRepository, times(0)).save(Mockito.any(Material.class));
    }
}
