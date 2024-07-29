package com.hb.system.ecommerce.shoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    List<OrderDetail> findByOrder_Id(int orderId);
}
