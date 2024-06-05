package com.hb.system.ecommerce.shoes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PRO_id;
    private String PRO_name;
    private String PRO_description;
    private String PRO_price_unit;
    private String PRO_url_image;
    private String PRO_tall;
    private String PRO_color;
    private String PRO_platform_size;
    private String PRO_heel_size;
    private String PRO_stock;
    private String PRO_state;
    @ManyToOne
    @JoinColumn(name = "CAT_id")
    private Category category;
}
