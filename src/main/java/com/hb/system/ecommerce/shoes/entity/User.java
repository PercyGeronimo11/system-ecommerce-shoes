package com.hb.system.ecommerce.shoes.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer USE_id;

    private String USE_name;
    private String USE_email;
    private String USE_password;
    private Integer USE_status;
    private LocalDateTime USE_register_date;
    private Integer ROL_id;

    // Getters y Setters
    public Integer getUSE_id() {
        return USE_id;
    }

    public void setUSE_id(Integer USE_id) {
        this.USE_id = USE_id;
    }

    public String getUSE_name() {
        return USE_name;
    }

    public void setUSE_name(String USE_name) {
        this.USE_name = USE_name;
    }

    public String getUSE_email() {
        return USE_email;
    }

    public void setUSE_email(String USE_email) {
        this.USE_email = USE_email;
    }

    public String getUSE_password() {
        return USE_password;
    }

    public void setUSE_password(String USE_password) {
        this.USE_password = USE_password;
    }

    public Integer getUSE_status() {
        return USE_status;
    }

    public void setUSE_status(Integer USE_status) {
        this.USE_status = USE_status;
    }

    public LocalDateTime getUSE_register_date() {
        return USE_register_date;
    }

    public void setUSE_register_date(LocalDateTime USE_register_date) {
        this.USE_register_date = USE_register_date;
    }

    public Integer getROL_id() {
        return ROL_id;
    }

    public void setROL_id(Integer ROL_id) {
        this.ROL_id = ROL_id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // Cambia según tus roles
    }

    @Override
    public String getPassword() {
        return USE_password;
    }

    @Override
    public String getUsername() {
        return USE_email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return USE_status == 1; // Asume que 1 es "activo"
    }
}
