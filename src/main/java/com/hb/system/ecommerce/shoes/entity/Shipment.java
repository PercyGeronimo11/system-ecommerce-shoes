package com.hb.system.ecommerce.shoes.entity;

import java.sql.Date;

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
@Table(name = "shipment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shi_id;

    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;
    private String shi_department;
    private String shi_province;
    private String shi_district;
    private String shi_adress;
    private Date   shi_date_start;
    private Date shi_date_end;
    private Integer shi_status;
}
