package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Shipment;
import com.hb.system.ecommerce.shoes.repositories.ShipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentService {
 
    private final ShipmentRepository shipmentRepository;

    public Shipment saveShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment getById(int id) {
        Optional<Shipment> shipment=shipmentRepository.findById(id);
        if(shipment.isPresent()){
            return shipment.get();
        }else{
            throw new RuntimeException("No se encontro material");
        }
    }
}
