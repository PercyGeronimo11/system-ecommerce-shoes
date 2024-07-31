package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.*;


import com.hb.system.ecommerce.shoes.repositories.*;

import com.hb.system.ecommerce.shoes.dto.request.PromoRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PromotionDetailService{

    @Autowired
    private PromotionDetailRepository promdetailRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Value("${url.local}")
    private String urlLocal;
    // Lista de detalles Activos
    public List<PromotionDetail> listAll() {
        return promdetailRepository.findByDetStatus(true);
    }

    // Lista de detalles Activos de una promocion
    public List<PromotionDetail> listAllDetPromotion(int promoid) {
        return promdetailRepository.findByPromotionIdAndDetStatus(promoid, true);
    }
    public Promotion save(PromoRequest promocion, MultipartFile file) throws IOException {
        try {
            Promotion promo = new Promotion();
            promo.setPromPercentage(promocion.getPromPercentage());
            promo.setPromStartdate(promocion.getPromStartdate());
            promo.setPromEnddate(promocion.getPromEnddate());
            promo.setPromDescription(promocion.getPromDescription());
            promo.setPromStatus(promocion.getPromStatus());
            promo.setPromUrlImage(saveFile(file));
            Promotion savedpromo = promotionRepository.save(promo);

            promocion.getPromDetail().forEach(PromoDetailRequest -> {
                PromotionDetail detail = new PromotionDetail();
                Product producto = new Product();
                Optional<Product> producFind = productRepository.findById(PromoDetailRequest.getId());
                if (producFind.isPresent()) {
                    producto = producFind.get();
                } else {
                    throw new RuntimeException("No se encontró el producto");
                }

                detail.setPromotion(savedpromo);
                detail.setDetDate(new Date());
                detail.setDetStatus(true);
                detail.setProduct(producto);
                promdetailRepository.save(detail);
            });
            return savedpromo;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la promocion", e);
        }
    }


    @Value("${image.upload.directory}")
    private String uploadDirectory;

    private String saveFile(MultipartFile archivo) {
        try {
            Path uploadPath = Paths.get(uploadDirectory);
            if (!uploadPath.toFile().exists()) {
                uploadPath.toFile().mkdirs();
            }
            String fileName = archivo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUrl = urlLocal +"/product/images/" + fileName;
            return fileUrl;
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
        }
    }
}
