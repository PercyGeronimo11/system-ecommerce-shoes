package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(OrderDetailId.class)

@Table(name = "order_detail")

public class OrderDetail {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ord_id")
    private Order order;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "pro_id")
    private Product product;

    @Column(nullable = false, name = "odt_amount")
    private int odt_amount;

    @Column(nullable = false, name = "odt_price")
    private float odt_price;

    @Column(nullable = false, name = "odt_status")
    private int odt_status;

    @Column(name = "odt_description")
    private String odt_description;


}