package com.hb.system.ecommerce.shoes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.entity.OrderDetailId;
import com.hb.system.ecommerce.shoes.services.OrderDetilService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/orderdetalle")
@RequiredArgsConstructor
@RestControllerAdvice
public class OrderDetailController {

    private final OrderDetilService orderDetailService;

    @PostMapping
    public ResponseEntity<String> createOrderDetails(@RequestBody List<OrderDetail> orderDetails){
        try {
            orderDetailService.createOrderDetails(orderDetails);
            return new ResponseEntity<>("Detalles creados exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear las reservas: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<OrderDetail> getAllOrdersDetails(){
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{ord_id}/{pro_id}")
    public ResponseEntity<OrderDetail> getByOrderDetailId(@PathVariable Integer ord_id, @PathVariable Integer pro_id){
        OrderDetailId orderDetailId = new OrderDetailId(ord_id, pro_id);
        return orderDetailService.getOrderDetailById(orderDetailId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{ord_id}/{pro_id}")
    public ResponseEntity<Void> updateOrderDetail(@PathVariable Integer ord_id, @PathVariable Integer pro_id, @RequestBody OrderDetail orderDetail) {
        OrderDetailId orderDetailId = new OrderDetailId(ord_id, pro_id);
        try {
            orderDetailService.updateOrderDetail(orderDetailId, orderDetail);
            return ResponseEntity.noContent().build();
        } catch (OrderDetilService.OrderDetailNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{ord_id}/{pro_id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Integer ord_id, @PathVariable Integer pro_id) {
        OrderDetailId orderDetailId = new OrderDetailId(ord_id, pro_id);
        try {
            orderDetailService.deleteOrderDetail(orderDetailId);
            return new ResponseEntity<>("Reserva eliminada exitosamente", HttpStatus.OK);
        } catch (OrderDetilService.OrderDetailNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (OrderDetilService.OrderDetailServiceException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(OrderDetilService.OrderDetailNotFoundException.class)
    public ResponseEntity<String> handleOrderDetailNotFoundException(OrderDetilService.OrderDetailNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderDetilService.OrderDetailServiceException.class)
    public ResponseEntity<String> handleOrderServiceDetailException(OrderDetilService.OrderDetailServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
