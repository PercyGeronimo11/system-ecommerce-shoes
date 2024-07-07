package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @Column(name = "pro_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category;
    @Column(name = "pro_name")
    private String proName;
    @Column(name = "pro_description")
    private String proDescription;
    @Column(name = "pro_unit_price")
    private Double proUnitPrice;
    @Column(name = "pro_unit_cost")
    private Double proUnitCost;
    @Column(name = "pro_size")
    private String proSize;
    @Column(name = "pro_size_platform")
    private String proSizePlatform;
    @Column(name = "pro_size_taco")
    private String proSizeTaco;
    @Column(name = "pro_url_image")
    private String proUrlImage;
    @Column(name = "pro_color")
    private String proColor;
    @Column(name = "pro_stock")
    private int proStock;
    @Column(name = "pro_status")
    private Boolean proStatus;
}
