package com.hb.system.ecommerce.shoes.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.repositories.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MaterialServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaterialRepository materialRepository;

    @BeforeEach
    public void setup() {
        materialRepository.deleteAll();
        Material material = new Material();
        material.setName("Test Material");
        material.setDescription("This is a test material");
        material.setStatus(true);
        materialRepository.save(material);
    }

    @Test
    public void testGetById() throws Exception {
        Material material = materialRepository.findAll().get(0);
        int id = material.getId();
        mockMvc.perform(get("/api/material/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.message").value("Detalle del producto recuperado exitossamente"))
                .andExpect(jsonPath("$.data.id").value(id))
                .andExpect(jsonPath("$.data.name").value("Test Material"))
                .andExpect(jsonPath("$.data.description").value("This is a test material"))
                .andExpect(jsonPath("$.data.status").value(true));
    }
}
