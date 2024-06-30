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
    @Column(name = "PROMT_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PROMT_id;

    @Column(name = "PROMT_percentage")
    private int PROMT_percentage;

    @Column(name = "PROMT_start_date")
    private Date PROMT_start_date;

    @Column(name = "PROMT_end_date")
    private Date PROMT_end_date;

    @Column(name = "PROMT_description")
    private String PROMT_description;

    @Column(name = "PROMT_status")
    private boolean PROMT_status;
}