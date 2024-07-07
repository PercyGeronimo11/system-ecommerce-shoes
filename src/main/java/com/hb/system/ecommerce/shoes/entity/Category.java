package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "CAT_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CAT_id;

    @Column(name = "CAT_name")
    private String CAT_name;

    @Column(name = "CAT_description")
    private String CAT_description;

    @Column(name = "CAT_status")
    private boolean CAT_status;
}
