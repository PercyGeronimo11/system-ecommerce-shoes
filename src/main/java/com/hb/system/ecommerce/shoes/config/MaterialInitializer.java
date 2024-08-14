package com.hb.system.ecommerce.shoes.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;
import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.repositories.MaterialRepository;

@Configuration
@RequiredArgsConstructor
public class MaterialInitializer {
    private final MaterialRepository materialRepository;

    @Bean
    @Transactional
    @Order(3)
    public CommandLineRunner initMaterials() {
        return args -> {
            if (materialRepository.count() == 0) {
                Material material1 = new Material();
                material1.setName("Cuero");
                material1.setPrice("11.50");
                material1.setQuantity("1");
                material1.setUnit("pie");
                material1.setDescription("Material 100% original");
                material1.setStatus(true);
                materialRepository.save(material1);

                Material material2 = new Material();
                material2.setName("Badana");
                material2.setPrice("2.30");
                material2.setQuantity("1");
                material2.setUnit("pie");
                material2.setDescription("Material 100% original");
                material2.setStatus(true);
                materialRepository.save(material2);

                Material material3 = new Material();
                material3.setName("Tacos");
                material3.setPrice("160.00");
                material3.setQuantity("12");
                material3.setUnit("unidades");
                material3.setDescription("Material 100% original");
                material3.setStatus(true);
                materialRepository.save(material3);

                Material material4 = new Material();
                material4.setName("Plataforma");
                material4.setPrice("160.00");
                material4.setQuantity("12");
                material4.setUnit("unidades");
                material4.setDescription("Material 100% original");
                material4.setStatus(true);
                materialRepository.save(material4);

                Material material5 = new Material();
                material5.setName("Huellas");
                material5.setPrice("8.00");
                material5.setQuantity("2");
                material5.setUnit("unidades");
                material5.setDescription("Material 100% original");
                material5.setStatus(true);
                materialRepository.save(material5);
            }
        };
    }
}
