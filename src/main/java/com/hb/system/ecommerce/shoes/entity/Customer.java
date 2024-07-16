package com.hb.system.ecommerce.shoes.entity;

import java.util.Date; 
import jakarta.persistence.Column;
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
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "use_id")
    private User usuario;

    @Column(name = "cust_firstname")
    private String custFirstName;

    @Column(name = "cust_lastname")
    private String custLastName;

    @Column(name = "cust_dni")
    private String custDni;

    @Column(name = "cust_department")
    private String custDepartment;

    @Column(name = "cust_birthdate")
    private Date custBirthDate; 

    @Column(name = "cust_city")
    private String custCity;

    @Column(name = "cust_province")
    private String custProvince;

    @Column(name = "cust_email")
    private String custEmail;

    @Column(name = "cust_password")
    private String custPassword;

    @Column(name = "cust_cellphone")
    private String custCellphone;

    @Column(name = "cust_status")
    private boolean custStatus;
}