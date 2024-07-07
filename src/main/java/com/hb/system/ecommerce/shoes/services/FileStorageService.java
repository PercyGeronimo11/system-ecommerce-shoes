package com.hb.system.ecommerce.shoes.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${upload.dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = uploadDir + File.separator + fileName;

        File dest = new File(filePath);
        file.transferTo(dest);

        return fileName; // Devuelve solo el nombre del archivo
    }

    public Path loadFile(String fileName) {
        return Paths.get(uploadDir).resolve(fileName);
    }

    public Resource loadFileAsResource(String fileName) throws IOException {
        Path filePath = loadFile(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists()) {
            return resource;
        } else {
            throw new IOException("File not found " + fileName);
        }
    }
}
