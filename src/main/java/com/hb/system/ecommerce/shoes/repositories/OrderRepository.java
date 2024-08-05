package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
