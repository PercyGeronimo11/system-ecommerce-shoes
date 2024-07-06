package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.dto.request.LotCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.LotEditReq;
import com.hb.system.ecommerce.shoes.dto.response.LotListResp;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import com.hb.system.ecommerce.shoes.repositories.LotRepository;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;
import com.hb.system.ecommerce.shoes.services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public LotListResp lotListService(String search) {
        List<Lot> lotList = lotRepository.findAll();
        return LotListResp.builder()
                .content(lotList)
                .build();
    }

    @Override
    public Lot lotStoreService(LotCreateReq lotCreateReq) throws IOException {
        try {
            Lot lot = new Lot();
            lot.setProName(lotCreateReq.getProName());
            lot.setProDescription(lotCreateReq.getProDescription());
            Optional<Category> categoryFind = categoryRepository.findById(lotCreateReq.getCategoryId());
            if (categoryFind.isPresent()) {
                lot.setCategory(categoryFind.get());
            } else {
                throw new RuntimeException("Categoría no encontrada");
            }
            Optional<Product> productFind = productRepository.findById(lotCreateReq.getProductId());
            if (productFind.isPresent()) {
                lot.setProduct(productFind.get());
            } else {
                throw new RuntimeException("Product no encontrada");
            }
            lot.setLotProductsAmount(lotCreateReq.getLotProductsAmount());
            lot.setProSizePlatform(lotCreateReq.getProSizePlatform());
            lot.setProSizeTacon(lotCreateReq.getProSizeTacon());
            lot.setLotTotalCost(lotCreateReq.getLotTotalCost());
            lot.setLotStatus(true);
            return lotRepository.save(lot);
        } catch (Exception e) {
            throw new RuntimeException("Error: Don't save the lot", e);
        }
    }

    @Override
    public Lot lotEditService(int id,LotEditReq lotEditReq){
            Optional<Lot> lotFind=lotRepository.findById(id);
            if(lotFind.isPresent()){
                lotFind.get().setProName(lotEditReq.getProName());
            }else{
                throw new RuntimeException("Categoría no encontrada");
            }

            lotFind.get().setProDescription(lotEditReq.getProDescription());
            Optional<Category> categoryFind = categoryRepository.findById(lotEditReq.getCategoryId());
            if (categoryFind.isPresent()) {
                lotFind.get().setCategory(categoryFind.get());
            } else {
                throw new RuntimeException("Categoría no encontrada");
            }
        Optional<Product> productFind = productRepository.findById(lotEditReq.getProductId());
        if (productFind.isPresent()) {
            lotFind.get().setProduct(productFind.get());
        } else {
            throw new RuntimeException("Product no encontrada");
        }
        lotFind.get().setLotProductsAmount(lotEditReq.getLotProductsAmount());
        lotFind.get().setProSizePlatform(lotEditReq.getProSizePlatform());
        lotFind.get().setProSizeTacon(lotEditReq.getProSizeTacon());
        lotFind.get().setLotTotalCost(lotEditReq.getLotTotalCost());
        lotFind.get().setLotStatus(true);
        return lotRepository.save(lotFind.get());
    }

}
