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

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
@RequiredArgsConstructor
@RestControllerAdvice
public class OrderController {
     private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> createOrder(@RequestBody Order order){
            Order orderResponse =  orderService.createOrder(order);
            ApiResponse<Order> response= new ApiResponse<>();
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Listado de órdenes exitoso");
            response.setData(orderResponse);
            return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping 
    public  ResponseEntity<ApiResponse<List<Order>>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        ApiResponse<List<Order>> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de órdenes exitoso");
        response.setData(orders);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    @GetMapping("/{ord_id}")
    public  java.util.Optional<Order> getByOrder(@PathVariable Integer ord_id){
        return orderService.getOrderById(ord_id);
    }

    @PatchMapping("/{ord_id}")
    public void updateOrder(@PathVariable Integer ord_id, @RequestBody Order order) {
        orderService.updateOrder(ord_id,order);
    }

    @DeleteMapping("/{ord_id}")
    public void deleteOrder(@PathVariable Integer ord_id) {
        orderService.deleteOrder(ord_id);;
    }

    @ExceptionHandler(OrderService.OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderService.OrderNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderService.OrderServiceException.class)
    public ResponseEntity<String> handleOrderServiceException(OrderService.OrderServiceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Error inesperado: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
