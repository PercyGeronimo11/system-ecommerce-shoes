package com.hb.system.ecommerce.shoes.services;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;

    public String createOrder(Order order) {
        try {
            orderRepository.save(order);
            return "Orden creada exitosamente";
        } catch (Exception e) {
            throw new OrderServiceException("Error al crear orden: " + e.getMessage());
        }
    }

    public java.util.List<Order> getAllOrders() {
        return orderRepository.findAll();
    } 
    
    public java.util.Optional<Order> getOrderById(int ord_id) {
        return orderRepository.findById(ord_id);
    }

    public String updateOrder(int ord_id,Order order) {
        if (orderRepository.existsById(ord_id)) {
            try {
                order.setOrd_id(ord_id);;;
                orderRepository.save(order);
                return "Orden  actualizada exitosamente";
            } catch (Exception e) {
                throw new OrderServiceException("Error al actualizar la orden: " + e.getMessage());
            }
        } else {
            throw new OrderNotFoundException("Orden no encontrado con licencia: " + ord_id );
        }
    }

    public String deleteOrder(int ord_id) {
        if (orderRepository.existsById(ord_id)) {
            try {
                orderRepository.deleteById(ord_id);
                return "Orden eliminada exitosamente";
            } catch (Exception e) {
                throw new OrderServiceException("Error al eliminar la orden: " + e.getMessage());
            }
        } else {
            throw new OrderNotFoundException("Order no encontrado con el id: " + ord_id);
        }
    }

    public class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public class OrderServiceException extends RuntimeException {
        public OrderServiceException(String message) {
            super(message);
        }
    }
}
