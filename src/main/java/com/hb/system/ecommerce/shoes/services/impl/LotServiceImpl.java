package com.hb.system.ecommerce.shoes.services.impl;

import com.hb.system.ecommerce.shoes.dto.request.LotRequest;
import com.hb.system.ecommerce.shoes.dto.request.LotDetailRequest;
import com.hb.system.ecommerce.shoes.dto.response.LotListResp;
import com.hb.system.ecommerce.shoes.entity.*;
import com.hb.system.ecommerce.shoes.repositories.*;
import com.hb.system.ecommerce.shoes.services.LotService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LotServiceImpl implements LotService {
    @Autowired
    private LotRepository lotRepository;
    @Autowired
    private LotDetailRepository lotDetailRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private LotDetailServiceImpl lotDetailServiceImpl;

    @Override
    public LotListResp lotListService(String search) {
        List<Lot> lotList = lotRepository.findAll();
        return LotListResp.builder()
                .content(lotList)
                .build();
    }

    @Override
    @Transactional
    public Lot lotStoreService(LotRequest lotRequest) throws IOException {
        try {
            Lot lot = new Lot();
            Optional<Product> productFind = productRepository.findById(lotRequest.getProductId());
            if (productFind.isPresent()) {
                lot.setProduct(productFind.get());
            } else {
                throw new RuntimeException("No se encontró el producto");
            }

            lot.setLotTotalCost(lotRequest.getLotTotalCost());
            lot.setLotQuantityProducts(lotRequest.getLotQuantityProducts());
            lot.setLotStatus(true);

            Lot savedLot = lotRepository.save(lot);

            lotRequest.getLotDetail().forEach(detailRequest->{
                LotDetail detail = new LotDetail();
                Optional<Material> materialFind = materialRepository.findById(detailRequest.getMaterialId());
                if (materialFind.isPresent()) {
                    detail.setMaterial(materialFind.get());
                    log.info("material:"+ materialFind.get());
                } else {
                    throw new RuntimeException("No se encontró el material");
                }
                detail.setDetQuantityMaterials(detailRequest.getDetQuantity());
                detail.setDetPriceUnit(detailRequest.getDetSubTotal());
                detail.setDetSubTotal(detailRequest.getDetSubTotal());
                log.info("finalll");
                LotDetail existingDetail = lotDetailRepository.findById(detail.getId()).orElse(null);
                if (existingDetail != null) {
                    lotDetailRepository.delete(existingDetail);
                }
                detail.setLot(savedLot);
                lotDetailRepository.save(detail);
                log.info("saliiii");
            });
            return savedLot;
        } catch (Exception e) {
            throw new RuntimeException("Error: No se pudo guardar el lote", e);
        }
    }

    @Override
    public Lot lotEditService(int id,LotRequest lotRequest){
        Lot existingLot = lotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lot not found with id: " + id));

        // Eliminar todos los detalles existentes del lote
        lotDetailRepository.deleteByLot_Id(existingLot.getId());

        for (LotDetailRequest detailRequest : lotRequest.getLotDetail()) {
            LotDetail detail = new LotDetail();
            detail.setLot(existingLot);
            Optional<Material> materialFind=materialRepository.findById(lotRequest.getProductId());
            if(materialFind.isPresent()){
                detail.setMaterial(materialFind.get());
            }else{
                throw new RuntimeException("No se encontro el material");
            }
            detail.setDetQuantityMaterials(detailRequest.getDetQuantity());
            detail.setDetSubTotal(detailRequest.getDetSubTotal());
            lotDetailRepository.save(detail);
        }

        return lotRepository.save(existingLot);
    }

}
