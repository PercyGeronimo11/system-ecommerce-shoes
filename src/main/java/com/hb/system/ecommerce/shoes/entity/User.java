package com.hb.system.ecommerce.shoes.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User implements UserDetails{
    @Id
    @GeneratedValue
    Integer id;
    @Column(nullable = false, name = "USE_name")
    String username;
    @Column(nullable = false, name = "USE_email")
    String email;
    @Column(nullable = false, name = "USE_password")
    String password;
    @Column(name = "USE_status")
    boolean status;
    @Column(name = "USE_register_date")
    LocalDateTime registerDate;
    @Column(name = "ROL_id")
    Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}

