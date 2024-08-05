package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "material")
public class Material {
    @Id
    @Column(name = "mat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mat_name")
    private String name;

    private Double price;

    private Integer quantity;

    @Column(name = "mat_unit")
    private String unit;

    @Column(name = "mat_description")
    private String description;

    private Boolean status;
}
