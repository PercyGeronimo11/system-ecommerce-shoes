package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "material")
public class Material {
    @Id
    @Column(name = "MAT_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "MAT_name")
    private String name;

    @Column(name = "MAT_unit_price")
    private String unitPrice;

    @Column(name = "MAT_stock")
    private String stock;

    @Column(name = "MAT_unit")
    private String unit;

    @Column(name = "MAT_description")
    private String description;

    @Column(name = "MAT_status")
    private boolean status;
}
