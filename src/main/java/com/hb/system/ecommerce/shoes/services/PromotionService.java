package com.hb.system.ecommerce.shoes.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.Path;

import com.hb.system.ecommerce.shoes.dto.request.LotRequest;
import com.hb.system.ecommerce.shoes.dto.request.PromoRequest;
import com.hb.system.ecommerce.shoes.dto.response.PromoCompleteResp;
import com.hb.system.ecommerce.shoes.dto.response.PromoDetailResp;
import com.hb.system.ecommerce.shoes.entity.Lot;
import com.hb.system.ecommerce.shoes.entity.LotDetail;
import com.hb.system.ecommerce.shoes.entity.Material;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.entity.PromotionDetail;

import jakarta.persistence.EntityNotFoundException;

import com.hb.system.ecommerce.shoes.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionDetailRepository promotionDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Promotion> listAll() {
        return promotionRepository.findAllActivePromotions();
    }

    public Promotion save(Promotion promotionRequest, MultipartFile file) throws IOException {
        try {
            Promotion promotion = new Promotion();
            promotion.setPromPercentage(promotionRequest.getPromPercentage());
            promotion.setPromStartdate(promotionRequest.getPromStartdate());
            promotion.setPromEnddate(promotionRequest.getPromEnddate());
            promotion.setPromDescription(promotionRequest.getPromDescription());
            promotion.setPromStatus(true);
            promotion.setPromUrlImage(saveFile(file));
            return promotionRepository.save(promotion);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the promotion", e);
        }
    }

    public PromoCompleteResp getById(int idpromocion) {
        Optional<Promotion> optionalPromo = promotionRepository.findById(idpromocion);
        List<PromotionDetail> promoDetails = promotionDetailRepository.findAllByPromotionId(idpromocion);
        List<PromoDetailResp> promoDetailRespList = new ArrayList<>();
        promoDetails.forEach(promoDetail -> {
            PromoDetailResp promoDetailResp = PromoDetailResp.builder()
                    .id(promoDetail.getId())
                    .proName(promoDetail.getProduct().getProName())
                    .build();
            promoDetailRespList.add(promoDetailResp);
        });

        PromoCompleteResp promoCompleteResp = PromoCompleteResp.builder()
                .id(optionalPromo.get().getId())
                .promPercentage(optionalPromo.get().getPromPercentage())
                .promStartdate(optionalPromo.get().getPromStartdate())
                .promEnddate(optionalPromo.get().getPromEnddate())
                .promDescription(optionalPromo.get().getPromDescription())
                .promUrlImage(optionalPromo.get().getPromUrlImage())
                .promoDetail(promoDetailRespList)
                .build();
        return promoCompleteResp;
    }

    public Promotion edit(int id, PromoRequest promoRequest, MultipartFile file) {
        try {
            Promotion exispromo = promotionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Lot not found with id: " + id));
            promotionDetailRepository.deleteByPromotionId(exispromo.getId());
            exispromo.setPromPercentage(promoRequest.getPromPercentage());
            exispromo.setPromStartdate(promoRequest.getPromStartdate());
            exispromo.setPromEnddate(promoRequest.getPromEnddate());
            exispromo.setPromDescription(promoRequest.getPromDescription());
            exispromo.setPromStatus(promoRequest.getPromStatus());
            exispromo.setPromUrlImage(saveFile(file));
            Promotion updatedPromo = promotionRepository.save(exispromo);

            // Vaciamos los detalles
            promotionDetailRepository.deleteAllByPromotionId(updatedPromo.getId());

            promoRequest.getPromDetail().forEach(promoDetail -> {
                PromotionDetail detail = new PromotionDetail();
                Optional<Product> productFind = productRepository.findById(promoDetail.getId()); 
                if (productFind.isPresent()) {
                    detail.setProduct(productFind.get());
                } else {
                    throw new RuntimeException("No se encontró el producto");
                }
                detail.setPromotion(updatedPromo);
                detail.setDetDate(new Date());
                detail.setDetStatus(true);
                promotionDetailRepository.save(detail);
            });
            return updatedPromo;
        } catch (Exception e) {
            throw new RuntimeException("Error al editar la promocion", e);
        }
    }

    /*
     * public void delete(int id) {
     * Promotion promotion = promotionRepository.findById(id);
     * if (promotion != null) {
     * promotion.setPromStatus(false);
     * promotionRepository.save(promotion);
     * } else {
     * throw new IllegalArgumentException("Promotion not found with id: " + id);
     * }
     * }
     */
    @Value("${image.upload.directory}")
    private String uploadDir;

    private String saveFile(MultipartFile archivo) {
        try {
            String uploadDir = "uploads";
            Path uploadPath = Paths.get(uploadDir);
            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs();
            }
            String fileName = archivo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = "/product/images/" + fileName;

            return fileUrl;
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
        }
    }
}
