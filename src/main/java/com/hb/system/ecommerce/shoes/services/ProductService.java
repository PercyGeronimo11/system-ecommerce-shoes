package com.hb.system.ecommerce.shoes.services;

import com.hb.system.ecommerce.shoes.dto.request.ProductReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductListResp productListService(String search);

    Product productStoreService(ProductReq productReq, MultipartFile urlImage)throws IOException;

    Product productGetService(int id);

    Product productEditService(int id,ProductReq productEditReq, MultipartFile urlImage);
}
