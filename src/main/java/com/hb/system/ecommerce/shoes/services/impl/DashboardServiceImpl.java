package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.repositories.*;
import com.hb.system.ecommerce.shoes.services.DashboardService;
import com.hb.system.ecommerce.shoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private LotRepository lotRepository;

    @Override
    public long getNumberSales(){
        return orderRepository.findByOrdStatus(4).stream().count();
    }
    @Override
    public long getNumberCustomers(){
        return customerRepository.count();
    }
    @Override
    public long getNumberProducts(){
        return productRepository.count();
    }

    @Override
    public BigDecimal getIncomeTotalService(){
        List<Order> orderList= orderRepository.findByOrdStatus(4);
        BigDecimal incomeTotal =BigDecimal.ZERO;

        for (Order order : orderList) {
            BigDecimal orderTotal = BigDecimal.valueOf(order.getOrd_total());
            incomeTotal = incomeTotal.add(orderTotal);
        }
        return incomeTotal.setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    public BigDecimal getCostTotalService(){
        List<Lot> lotList = lotRepository.findByLotStatus(true);
        BigDecimal costTotal =
        BigDecimal.ZERO;
        for (Lot lot : lotList) {
            BigDecimal lotCost = BigDecimal.valueOf(lot.getLotTotalCost());
            costTotal = costTotal.add(lotCost);
        }
        return costTotal.setScale(2, RoundingMode.HALF_UP);
    }

}
