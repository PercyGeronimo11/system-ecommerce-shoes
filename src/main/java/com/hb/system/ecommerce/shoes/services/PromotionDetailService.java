package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.hb.system.ecommerce.shoes.entity.*;

import com.hb.system.ecommerce.shoes.repositories.*;
import com.hb.system.ecommerce.shoes.dto.request.*;
import com.hb.system.ecommerce.shoes.dto.response.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@Service
public class PromotionDetailService {

    @Autowired
    private PromotionDetailRepository promdetailRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionDetailRepository promotionDetailRepository;
    @Value("${url.local}")
    private String urlLocal;

    public List<Promotion> listAll() {
        return promotionRepository.findAll();
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

    // Lista de detalles Activos de una promocion
    public Promotion retornaPromotion(int productoid) {
        PromotionDetail detalle=promotionDetailRepository.findByProductId(productoid);
        return  detalle.getPromotion();
    }

    // Obtener una promocion y su detalle
    public PromoCompleteResp getById(int idpromocion) {
        Optional<Promotion> optionalPromo = promotionRepository.findById(idpromocion);
        List<PromotionDetail> promoDetails = promotionDetailRepository.findAllByPromotion(optionalPromo.get());
        List<PromoDetailResp> promoDetailRespList = new ArrayList<>();
        promoDetails.forEach(promoDetail -> {
            PromoDetailResp promoDetailResp = PromoDetailResp.builder()
                    .id(promoDetail.getProduct().getId())
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
                .promStatus(optionalPromo.get().getPromStatus())
                .promoDetail(promoDetailRespList)
                .build();
        return promoCompleteResp;
    }

    public Promotion edit(int idpromocion, PromoRequest promocion, MultipartFile file) throws IOException {
        try {
            Optional<Promotion> optionalPromo = promotionRepository.findById(idpromocion);
            if (!optionalPromo.isPresent()) {
                throw new RuntimeException("No se encontró la promoción");
            }
            Promotion promo = optionalPromo.get();
            promo.setPromPercentage(promocion.getPromPercentage());
            promo.setPromStartdate(promocion.getPromStartdate());
            promo.setPromEnddate(promocion.getPromEnddate());
            promo.setPromDescription(promocion.getPromDescription());
            promo.setPromStatus(promocion.getPromStatus());

            if (file != null && !file.isEmpty()) {
                promo.setPromUrlImage(saveFile(file));
            }
            // promocion actualizada
            Promotion updatedPromo = promotionRepository.save(promo);

            // Vaciamos los detalles
            List<PromotionDetail> lista=promotionDetailRepository.findAllByPromotion(updatedPromo);
            for (PromotionDetail detail : lista) {
                promotionDetailRepository.deleteById(detail.getId());
            }


            // Guardar nuevos detalles
            promocion.getPromDetail().forEach(PromoDetailRequest -> {
                PromotionDetail detail = new PromotionDetail();
                Optional<Product> producFind = productRepository.findById(PromoDetailRequest.getId());
                if (producFind.isPresent()) {
                    detail.setProduct(producFind.get());
                } else {
                    throw new RuntimeException("No se encontró el producto");
                }

                detail.setPromotion(updatedPromo);
                detail.setDetDate(new Date());
                detail.setDetStatus(true);
                promdetailRepository.save(detail);
            });
            return updatedPromo;
        } catch (Exception e) {
            throw new RuntimeException("Error al editar la promocion", e);
        }
    }

    public void delete(int id) {
        Optional<Promotion> promo = promotionRepository.findById(id);
        if (promo.isPresent()) {
            List<PromotionDetail> lista = promotionDetailRepository.findAllByPromotion(promo.get());
            for (PromotionDetail detail : lista) {
                detail.setDetStatus(false);
                promotionDetailRepository.save(detail);
            }
          promo.get().setPromStatus(false);
          promotionRepository.save(promo.get());
        } else {
            throw new IllegalArgumentException("Promotion not found with id: " + id);
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

            String fileUrl = urlLocal + "/product/images/" + fileName;
            return fileUrl;
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
        }
    }
}
