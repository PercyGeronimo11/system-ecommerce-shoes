package com.hb.system.ecommerce.shoes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Entity
    @Builder
    @Table(name = "product_customer")
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProductCustomer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne()
        @JoinColumn(name = "customerId", referencedColumnName = "id")
        private Customer customer;

        @ManyToOne()
        @JoinColumn(name = "productId", referencedColumnName = "id")
        private Product product;

        private Integer rating;

        private Integer clicks;
    }