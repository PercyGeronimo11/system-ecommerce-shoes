package com.hb.system.ecommerce.shoes.services;

import com.hb.system.ecommerce.shoes.dto.request.ProductCustomerReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductCustomerResp;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.entity.ProductCustomer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductCustomerService {
    void saveProductCustomerService(ProductCustomerReq productCustomer);
    void increaseClicksProductService(ProductCustomerReq productCustomer);
    List<ProductCustomerResp> findByCustomerIdService(Integer customerId);
}
