package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hb.system.ecommerce.shoes.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    //Optional<Order> findByid(int id);
    @Query("SELECT o FROM Order o WHERE o.ord_status IN :statusList")
    List<Order> findAllByStatusList(@Param("statusList") List<Integer> statusList);
}
