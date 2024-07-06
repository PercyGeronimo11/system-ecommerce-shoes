package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lot {
    @Id
    @Column(name = "lot_id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Product product;
    @Column(name = "lot_total_cost")
    private String lotTotalCost;
    @Column(name = "lot_products_amount")
    private String lotProductsAmount;
    @Column(name = "pro_description")
    private String proDescription;
    @Column(name = "pro_name")
    private String proName;
    @Column(name = "pro_size_platform")
    private String proSizePlatform;
    @Column(name = "pro_size_tacon")
    private String proSizeTacon;
    @Column(name = "pro_unit_price")
    private String proStock;
    @Column(name = "lot_status")
    private Boolean lotStatus;
}
