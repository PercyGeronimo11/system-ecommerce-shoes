package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @Column(name = "pro_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category;

    private String proName;

    private String proDescription;

    private Double proUnitPrice;

    private BigDecimal proUnitCost;

    private String proSize;

    private String proSizePlatform;

    private String proSizeTaco;

    private String proUrlImage;

    private String proColor;

    private int proStock;

    private Boolean proStatus;
}
