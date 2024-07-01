package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
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
    private String proUnitPrice;
    @Column(name = "pro_size")
    private String proSize;
    @Column(name = "pro_size_platform")
    private String proSizePlatform;
    @Column(name = "pro_size_tacon")
    private String proSizeTacon;
    @Column(name = "pro_url_image")
    private String proUrlImage;
    @Column(name = "pro_color")
    private String proColor;
    @Column(name = "pro_stock")
    private String proStock;
    @Column(name = "pro_status")
    private String proStatus;
}
