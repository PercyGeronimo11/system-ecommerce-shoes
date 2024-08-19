package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.Order;
import com.hb.system.ecommerce.shoes.repositories.*;
import com.hb.system.ecommerce.shoes.services.DashboardService;
import com.hb.system.ecommerce.shoes.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
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

    @Override
    @Transactional
    public List<Map<String, Object>> getWeeklyIncome() {
        LocalDate today = LocalDate.now();
        log.info("Fecha actual de hoy: " + today);

        LocalDate startOfRange = today.minusWeeks(1);
        LocalDate endOfRange = today.minusDays(1);

        // Convertir las fechas a Date
        Date startDate = Date.from(startOfRange.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfRange.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        List<Map<String, Object>> result = new ArrayList<>();
        try {
            List<Order> orders = orderRepository.findOrdersBetweenOrdDate(startDate, endDate);

            // Calcular el ingreso diario
            Map<LocalDate, BigDecimal> dailyIncome = new HashMap<>();
            for (LocalDate date = startOfRange; !date.isAfter(endOfRange); date = date.plusDays(1)) {
                dailyIncome.put(date, BigDecimal.ZERO);
            }
            for (Order order : orders) {
                LocalDate orderDate = order.getOrdDate().toLocalDate(); // Asegúrate de que getOrdDate() sea de tipo java.sql.Date
                if (dailyIncome.containsKey(orderDate)) {
                    BigDecimal currentTotal = dailyIncome.get(orderDate);
                    dailyIncome.put(orderDate, currentTotal.add(BigDecimal.valueOf(order.getOrd_total())));
                }
            }

            // Convertir al formato deseado
            for (LocalDate date = startOfRange; !date.isAfter(endOfRange); date = date.plusDays(1)) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("day", date.getDayOfWeek().toString().toLowerCase());
                entry.put("numDay", date.getDayOfMonth());
                entry.put("income", dailyIncome.get(date).setScale(2, BigDecimal.ROUND_HALF_UP));
                result.add(entry);
            }
        } catch (Exception e) {
            log.error("Error al obtener los ingresos semanales: ", e);
            throw new RuntimeException("Error al procesar la información de ingresos semanales.", e);
        }

        return result;
    }

    public long getCustomerCountLast7Days() {
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date sevenDaysAgo = calendar.getTime();

        return customerRepository.countCustomersRegisteredBetween(sevenDaysAgo, today);
    }
}
