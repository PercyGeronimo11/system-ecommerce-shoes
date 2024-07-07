package com.hb.system.ecommerce.shoes.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
@Service
@RequiredArgsConstructor
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Value("${image.upload.directory}")
    private String uploadDir;
    public List<Promotion> listAll() {
        return promotionRepository.findAllActivePromotions();
    }

    public Promotion getById(int id) {
        return promotionRepository.findById(id);
    }
    public Promotion save(Promotion promotionRequest, MultipartFile file) throws IOException {
        promotionRequest.setPromStatus(true);
        if (file != null && !file.isEmpty()) {
            String fileName = saveImage(file);
            promotionRequest.setPromUrlImage(fileName);
        }
        return promotionRepository.save(promotionRequest);
    }

    public Promotion update(int id, Promotion promotionRequest, MultipartFile file) throws IOException {
        if (promotionRepository.existsById(id)) {
            promotionRequest.setId(id);
            if (file != null && !file.isEmpty()) {
                String fileName = saveImage(file);
                promotionRequest.setPromUrlImage(fileName);
            }
            return promotionRepository.save(promotionRequest);
        } else {
            throw new IllegalArgumentException("Promotion not found with id: " + id);
        }
    }

    public void delete(int id) {
        Promotion promotion = promotionRepository.findById(id);
        if (promotion != null) {
            promotion.setPromStatus(false);
            promotionRepository.save(promotion);
        } else {
            throw new IllegalArgumentException("Promotion not found with id: " + id);
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + fileName);
        Files.copy(file.getInputStream(), path);
        return fileName;
    }
}
