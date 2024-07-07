package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pro_id", referencedColumnName = "pro_id")
    private Product product;

    @Column(name = "lot_total_cost")
    private Double lotTotalCost;

    @Column(name = "lot_quantity_products")
    private Integer lotQuantityProducts;

    @Column(name = "lot_status")
    private Boolean lotStatus;
}
