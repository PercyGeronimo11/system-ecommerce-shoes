package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.entity.OrderDetailId;
import com.hb.system.ecommerce.shoes.repositories.OrderDetailRepository;
import com.hb.system.ecommerce.shoes.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetilService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    public void createOrderDetails(List<OrderDetail> orderDetails) {
        try {
            orderDetailRepository.saveAll(orderDetails);
        } catch (Exception e) {
            throw new OrderDetailServiceException("Error al crear las reservas: " + e.getMessage());
        }
    }

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public List<OrderDetail> OrderDetailfindOrder(Integer idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        return orderDetailRepository.findByOrder(order); 
    }

    public Optional<OrderDetail> getOrderDetailById(OrderDetailId id) {
        return orderDetailRepository.findById(id);
    }

    public void updateOrderDetail(OrderDetailId orderDetailId, OrderDetail orderDetail) {
        Optional<OrderDetail> existingOrderDetailOpt = orderDetailRepository.findById(orderDetailId);
        if (existingOrderDetailOpt.isPresent()) {
            OrderDetail existingOrderDetail = existingOrderDetailOpt.get();
            existingOrderDetail.setOdt_amount(orderDetail.getOdt_amount());
            existingOrderDetail.setOdt_price(orderDetail.getOdt_price());
            existingOrderDetail.setOdt_status(orderDetail.getOdt_status());

            orderDetailRepository.save(existingOrderDetail);
        } else {
            throw new OrderDetailNotFoundException("BookingDetail not found");
        }
    }

    public void deleteOrderDetail(OrderDetailId id) {
        if (orderDetailRepository.existsById(id)) {
            try {
                orderDetailRepository.deleteById(id);
            } catch (Exception e) {
                throw new OrderDetailServiceException("Error al eliminar la reserva: " + e.getMessage());
            }
        } else {
            throw new OrderDetailNotFoundException("Reserva no encontrada con el id: " + id);
        }
    }

    public static class OrderDetailNotFoundException extends RuntimeException {
        public OrderDetailNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderDetailServiceException extends RuntimeException {
        public OrderDetailServiceException(String message) {
            super(message);
        }
    }
}
