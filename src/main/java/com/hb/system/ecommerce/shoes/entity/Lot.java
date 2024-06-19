package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Lot {
    @Id
    @Column(name = "lot_id")
    private int id;
    //private Category category;
    private String pro_name;
    private String pro_description;
    private String pro_unit_price;
    private String pro_size_platform;
    private String pro_size_tacon;
}
