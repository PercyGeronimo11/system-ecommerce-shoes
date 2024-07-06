package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LotDetail {
    @Id
    @Column(name = "lotDetail_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "lot_id", referencedColumnName = "lot_id")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "mat_id", referencedColumnName = "mat_id")
    private Material material;

    @Column(name = "det_quantity_material")
    private String detQuantityMaterials;

    @Column(name = "det_sub_total")
    private String detSubTotal;
}