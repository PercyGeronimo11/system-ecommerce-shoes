package com.hb.system.ecommerce.shoes.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "prom_id", referencedColumnName = "id")
    private Promotion promotion;
    @ManyToOne()
    @JoinColumn(name = "prod_id", referencedColumnName = "id")
    private Product product;
    private Date detDate;
    private boolean detStatus;
}
