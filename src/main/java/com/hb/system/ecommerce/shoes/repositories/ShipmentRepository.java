package com.hb.system.ecommerce.shoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.system.ecommerce.shoes.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {

}
