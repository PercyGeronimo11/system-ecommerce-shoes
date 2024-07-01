package com.hb.system.ecommerce.shoes.services;

import com.hb.system.ecommerce.shoes.dto.request.ProductCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductEditReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductListReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductListResp productListService(ProductListReq productListReq);

    Product productStoreService(ProductCreateReq productCreateReq, MultipartFile urlImage)throws IOException;

    Product productEditService(int id,ProductEditReq productEditReq, MultipartFile urlImage);
}
