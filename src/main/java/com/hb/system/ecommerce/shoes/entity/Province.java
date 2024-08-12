package com.hb.system.ecommerce.shoes.entity;

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
@Table(name = "provincias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProv;

    private String Provincia;

    @ManyToOne
    @JoinColumn(name = "idDepa")
    private Department department;

    public Province(String Provincia, Department department) {
        this.Provincia = Provincia;
        this.department = department;
    }
}
