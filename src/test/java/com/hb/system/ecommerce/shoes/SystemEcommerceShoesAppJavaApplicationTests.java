package com.hb.system.ecommerce.shoes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.repositories.MaterialRepository;
import com.hb.system.ecommerce.shoes.services.MaterialService;

@SpringBootTest
class SystemEcommerceShoesAppJavaApplicationTests {
    
	@Autowired
	private MaterialRepository materialRepository;
	
	@Autowired
    private MaterialService materialService;

    @Test
    public void testFindById() {
        Material material = new Material();
        material.setName("Leather");
        material.setPrice(10.0);
        material.setQuantity(100);
        material.setUnit("meters");
        material.setDescription("High quality leather");
        material.setStatus(true);
        material = materialRepository.save(material);

        Optional<Material> foundMaterial = materialRepository.findById(material.getId());
        assertTrue(foundMaterial.isPresent());
    }

	 @BeforeEach
    public void setup() {
        materialRepository.deleteAll();
    }

    @Test
    public void testGetById() {
        Material material = new Material();
        material.setName("Leather");
        material = materialRepository.save(material);

        Material foundMaterial = materialService.getById(material.getId());
        assertEquals("Leather", foundMaterial.getName());
    }

    @Test
    public void testGetById_NotFound() {
        assertThrows(RuntimeException.class, () -> {
            materialService.getById(1);
        });
    }

	
}
