package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
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

    private Integer proAverageRating;

    private Boolean proStatus;
}
