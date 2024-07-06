package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LotDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_det_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "lot_id")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "mat_id", referencedColumnName = "mat_id")
    private Material material;

    @Column(name = "det_price_unit")
    private Double detPriceUnit;

    @Column(name = "det_quantity_material")
    private Integer detQuantityMaterials;

    @Column(name = "det_sub_total")
    private Double detSubTotal;
}