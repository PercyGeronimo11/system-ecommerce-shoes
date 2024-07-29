package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    List<Order> findByOrdStatus(boolean ordStatus);
    Optional<Order> findById(Integer id);
}
