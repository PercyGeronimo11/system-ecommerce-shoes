package com.hb.system.ecommerce.shoes.controllers;

import com.hb.system.ecommerce.shoes.dto.ApiResponse;
import com.hb.system.ecommerce.shoes.dto.request.ProductCustomerReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductCustomerResp;
import com.hb.system.ecommerce.shoes.entity.ProductCustomer;
import com.hb.system.ecommerce.shoes.services.impl.ProductCustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@Controller
@RequestMapping("/api/product-customer")
public class ProductCustomerController {
    @Autowired
    private ProductCustomerServiceImpl productCustomerService;

    @PostMapping(value = "/save/ratings",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<ProductCustomer>> rateProduct(@RequestBody ProductCustomerReq productCustomer) {
        productCustomerService.saveProductCustomerService(productCustomer);
        ApiResponse<ProductCustomer> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se a guardado el producto exitosamente");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/update/clicks",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ApiResponse<ProductCustomer>> updateClicks(@RequestBody ProductCustomerReq productCustomer) {
        productCustomerService.increaseClicksProductService(productCustomer);
        ApiResponse<ProductCustomer> response= new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Se a guardado el producto exitosamente");
        response.setData(null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping( value = "/get/ratings/{customerId}")
    public ResponseEntity<ApiResponse<List<ProductCustomerResp>>> getProductRatingsByCustomer(@PathVariable Integer customerId) {
        List<ProductCustomerResp> productCustomers = productCustomerService.findByCustomerIdService(customerId);
        ApiResponse<List<ProductCustomerResp>> response = new ApiResponse<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Calificaciones obtenidas exitosamente");
        response.setData(productCustomers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
