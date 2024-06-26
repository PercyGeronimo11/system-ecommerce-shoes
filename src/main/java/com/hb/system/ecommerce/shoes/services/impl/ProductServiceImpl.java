package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.dto.request.ProductCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductListReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;
import com.hb.system.ecommerce.shoes.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductListResp productListService(ProductListReq productListReq){
        List<Product> productList=productRepository.findByProNameContaining(productListReq.getSearch());
        return ProductListResp.builder()
                .content(productList)
                .build();
    }

    @Override
    public Product productStoreService(ProductCreateReq productCreateReq){
        Product product=new Product();
        product.setProName(productCreateReq.getProName());
        product.setProDescription(product.getProDescription());
        Category category= new Category();
        category.setId(productCreateReq.getCatId());
        product.setCategory(category);
        product.setProUnitPrice(productCreateReq.getProUnitPrice());
        product.setProSizePlatform(productCreateReq.getProSizePlatform());
        product.setProSizeTacon(productCreateReq.getProSizeTacon());
        return productRepository.save(product);
    }
}
