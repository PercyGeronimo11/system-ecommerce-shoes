package com.hb.system.ecommerce.shoes.services;


import java.math.BigDecimal;

public interface DashboardService {
    long getNumberSales();
    long getNumberProducts();
    long getNumberCustomers();
    BigDecimal getIncomeTotalService();
    BigDecimal getCostTotalService();
}
