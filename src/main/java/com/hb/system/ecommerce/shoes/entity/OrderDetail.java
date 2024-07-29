package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "ord_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "pro_id", referencedColumnName = "id")
    private Product product;

    private Integer detQuantity;
    private Double detPrice;
    private String detDescription;
    private boolean detStatus;
}
