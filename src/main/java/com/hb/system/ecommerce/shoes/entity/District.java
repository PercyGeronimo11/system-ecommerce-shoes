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
@Table(name = "distritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDist;

    private String Distrito;

    @ManyToOne
    @JoinColumn(name = "idProv")
    private Province province;

    public District(String Distrito, Province province) {
        this.Distrito = Distrito;
        this.province = province;
    }
}
