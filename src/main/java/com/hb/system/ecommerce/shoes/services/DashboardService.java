package com.hb.system.ecommerce.shoes.services;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DashboardService {
    long getNumberSales();
    long getNumberProducts();
    long getNumberCustomers();
    BigDecimal getIncomeTotalService();
    BigDecimal getCostTotalService();
    List<Map<String, Object>> getWeeklyIncome();
    long getCustomerCountLast7Days();
}
