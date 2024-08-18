package com.hb.system.ecommerce.shoes.controllers;

import com.hb.system.ecommerce.shoes.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@Controller
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(value = { "/index" },
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Map<String, Object> getTableCounts() {
        Map<String, Object> counts = new HashMap<>();
        counts.put("numCustomers", dashboardService.getNumberCustomers());
        counts.put("numProducts", dashboardService.getNumberProducts());
        counts.put("numSales", dashboardService.getNumberSales());
        counts.put("incomeTotal", dashboardService.getIncomeTotalService());
        counts.put("costTotal", dashboardService.getCostTotalService());
        return counts;
    }
}
