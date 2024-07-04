package com.hb.system.ecommerce.shoes.entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    @Id
    @Column(name = "prom_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prom_percentage")
    private int promPercentage;

    @Column(name = "prom_start_date")
    private Date promStartdate;

    @Column(name = "prom_end_date")
    private Date promEnddate;

    @Column(name = "prom_description")
    private String promDescription;

    @Column(name = "prom_url_image")
    private String promUrlImage;

    @Column(name = "prom_status")
    private boolean promStatus;
}