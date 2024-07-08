package com.hb.system.ecommerce.shoes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;
    
    @Column(nullable = false, unique = true, name = "rol_name")
    private String name;

    @Column(nullable = false, name = "rol_status")
    private boolean status;

    @Column(name = "rol_description")
    private boolean description;
}