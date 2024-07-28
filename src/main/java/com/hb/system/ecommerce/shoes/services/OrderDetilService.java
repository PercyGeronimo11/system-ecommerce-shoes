package com.hb.system.ecommerce.shoes.services;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.OrderDetail;
import com.hb.system.ecommerce.shoes.entity.OrderDetailId;
import com.hb.system.ecommerce.shoes.repositories.OrderDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class OrderDetilService {
    private final OrderDetailRepository orderDetailRepository;

    public String createOrderDetail(OrderDetail orderDetail) {
        try {
            orderDetailRepository.save(orderDetail);
            return "Detalle  creado exitosamente";
        } catch (Exception e) {
            throw new OrderDetailServiceException("Error al crear la reserva: " + e.getMessage());
        }
    }

    public java.util.List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public java.util.Optional<OrderDetail> getOrderDetailById(OrderDetailId id) {
        return orderDetailRepository.findById(id);
    }

    public void updateOrderDetail(OrderDetailId orderDetailId, OrderDetail orderDetail) {
        java.util.Optional<OrderDetail> existingOrderDetailOpt = orderDetailRepository.findById(orderDetailId);
        if (existingOrderDetailOpt.isPresent()) {
            OrderDetail existingOrderDetail = existingOrderDetailOpt.get();
            // Mapear los valores del objeto recibido a la entidad existente
            existingOrderDetail.setOdt_amount(orderDetail.getOdt_amount());
            existingOrderDetail.setOdt_price(orderDetail.getOdt_price());
            existingOrderDetail.setOdt_status(orderDetail.getOdt_status());

            orderDetailRepository.save(existingOrderDetail);
            
        } else {
            throw new OrderDetailNotFoundException("BookingDetail not found");
        }
    }

    public String deleteOrderDetail(OrderDetailId id) {
        if (orderDetailRepository.existsById(id)) {
            try {
                orderDetailRepository.deleteById(id);
                return "Reserva eliminada exitosamente";
            } catch (Exception e) {
                throw new OrderDetailServiceException("Error al eliminar la reserva: " + e.getMessage());
            }
        } else {
            throw new OrderDetailNotFoundException("Reserva no encontrada con el id: " + id);
        }
    }


    
    public class OrderDetailNotFoundException extends RuntimeException {
        public OrderDetailNotFoundException(String message) {
            super(message);
        }
    }

    public class OrderDetailServiceException extends RuntimeException {
        public OrderDetailServiceException(String message) {
            super(message);
        }
    }
}
