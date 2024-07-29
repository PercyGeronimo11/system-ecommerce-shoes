package com.hb.system.ecommerce.shoes.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.services.MaterialService;
import com.hb.system.ecommerce.shoes.services.OrderService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Order>>> listOrders(){
        List<Order> orders = orderService.listAll();
        ApiResponse<List<Order>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Listado de detalles de órdenes exitoso");
        response.setData(orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<ApiResponse<List<OrderDetail>>> listOrderDetailByOrder(@PathVariable int id){
        List<OrderDetail> orders = orderService.OrderDetailfindOrder(id);
        ApiResponse<List<OrderDetail>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
            response.setMessage("Listado de detalles de órdenes exitoso");
            response.setData(orders);
        
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
