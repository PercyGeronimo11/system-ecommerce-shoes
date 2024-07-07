package com.hb.system.ecommerce.shoes.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.hb.system.ecommerce.shoes.entity.Promotion;
import com.hb.system.ecommerce.shoes.repositories.PromotionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PromotionService {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private FileStorageService fileStorageService;

    public List<Promotion> listAll() {
        return promotionRepository.findAllActivePromotions();
    }

    public Promotion getById(int id) {
        return promotionRepository.findById(id);
    }

    public Promotion save(Promotion resource, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file);
            String fileUrl = "/api/promotion/images/" + fileName;
            resource.setPromUrlImage(fileUrl);
        }
        resource.setPromStatus(true);
        return promotionRepository.save(resource);
    }

    public Promotion update(int id, Promotion resource, MultipartFile file) throws IOException {
        if (promotionRepository.existsById(id)) {
            if (file != null && !file.isEmpty()) {
                String fileName = fileStorageService.storeFile(file);
                String fileUrl = "/api/promotion/images/" + fileName;
                resource.setPromUrlImage(fileUrl);
            }
            resource.setId(id);
            return promotionRepository.save(resource);
        } else {
            throw new IllegalArgumentException("Promotion not found with id: " + id);
        }
    }

    public void delete(int id) {
        Promotion promocion = promotionRepository.findById(id);
        promocion.setPromStatus(false);
        promotionRepository.save(promocion);
    }

    public Resource loadFileAsResource(String fileName) throws IOException {
        return fileStorageService.loadFileAsResource(fileName);
    }
}
