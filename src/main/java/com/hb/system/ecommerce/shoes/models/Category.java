package com.hb.system.ecommerce.shoes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "CAT_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CAT_name")
    private String name;

    @Column(name = "CAT_description")
    private String description;

    @Column(name = "CAT_status")
    private boolean status;

    public Category(){}

    public Category(int id, String name, String description, boolean state) {
        this.id= id;
        this.name = name;
        this.description = description;
        this.status = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return status;
    }

    public void setState(Boolean status) {
        this.status = status;
    }

}
