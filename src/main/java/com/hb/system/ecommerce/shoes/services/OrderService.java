package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.repositories.OrderDetailRepository;
import com.hb.system.ecommerce.shoes.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order> listAll() {
        return orderRepository.findByOrdStatus(true);
    }

    public List<OrderDetail> OrderDetailfindOrder(Integer idOrder) {
        return orderDetailRepository.findAll(); 
    }
}
