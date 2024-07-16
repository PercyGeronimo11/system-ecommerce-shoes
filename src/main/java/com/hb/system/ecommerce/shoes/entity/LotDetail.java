package com.hb.system.ecommerce.shoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LotDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_det_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lot_id", referencedColumnName = "lot_id")
    @JsonIgnore
    private Lot lot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mat_id", referencedColumnName = "mat_id")
    @JsonIgnore
    private Material material;

    @Column(name = "det_price_unit")
    private Double detPriceUnit;

    @Column(name = "det_quantity_material")
    private Integer detQuantity;

    @Column(name = "det_sub_total")
    private Double detSubTotal;
}