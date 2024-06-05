package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column(name = "pro_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id")
    private Category category;

    private String pro_name;
    private String pro_description;
    private String pro_unit_price;
    private String pro_size_platform;
    private String pro_size_tacon;

}
