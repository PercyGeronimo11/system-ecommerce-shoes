package com.hb.system.ecommerce.shoes.controllers;

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
    public void createOrderDetail(@RequestBody OrderDetail orderDetail){
        orderDetailService.createOrderDetail(orderDetail);
    }

    @GetMapping
    public java.util.List<OrderDetail> getAllOrdersDetails(){
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{ord_id}/{pro_id}")
    public java.util.Optional<OrderDetail> getByOrderDetailId(@PathVariable Integer ord_id, @PathVariable Integer pro_id){
        OrderDetailId orderDetailId = new OrderDetailId(ord_id, pro_id);
        return orderDetailService.getOrderDetailById(orderDetailId);
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
    public void deleteOrderDetail(@PathVariable Integer ord_id, @PathVariable Integer pro_id) {
        OrderDetailId orderDetailId = new OrderDetailId(ord_id,pro_id);
        orderDetailService.deleteOrderDetail(orderDetailId);
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
