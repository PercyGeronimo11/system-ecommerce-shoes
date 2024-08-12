package com.hb.system.ecommerce.shoes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Shipment;
import com.hb.system.ecommerce.shoes.services.ShipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shipment")
@RequiredArgsConstructor
@RestControllerAdvice
public class ShipmentController {

    private final ShipmentService shipmentService;
    
    @PostMapping
    public ResponseEntity<ApiResponse<Shipment>> createShipment(@RequestBody Shipment shipment) {
        Shipment savedShipment = shipmentService.saveShipment(shipment);
        ApiResponse<Shipment> response = new ApiResponse<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Envío registrado exitosamente");
        response.setData(savedShipment);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
 
    // Obtener todos los envíos
    @GetMapping
    public ResponseEntity<ApiResponse<List<Shipment>>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        ApiResponse<List<Shipment>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de envíos exitoso");
        response.setData(shipments);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
     
    @GetMapping("/{shi_id}")
    public ResponseEntity<ApiResponse<Shipment>> getById(@PathVariable int shi_id){
        Shipment shipment= shipmentService.getById(shi_id);
        ApiResponse<Shipment> response=new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Detalle del producto recupersado exitossamente");
        response.setData(shipment);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
  

    
    
}
