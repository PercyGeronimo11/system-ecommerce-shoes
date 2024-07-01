package com.hb.system.ecommerce.shoes.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hb.system.ecommerce.shoes.dto.request.ProductCreateReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductEditReq;
import com.hb.system.ecommerce.shoes.dto.request.ProductListReq;
import com.hb.system.ecommerce.shoes.dto.response.ProductListResp;
import com.hb.system.ecommerce.shoes.entity.Category;
import com.hb.system.ecommerce.shoes.entity.Product;
import com.hb.system.ecommerce.shoes.repositories.CategoryRepository;
import com.hb.system.ecommerce.shoes.repositories.ProductRepository;
import com.hb.system.ecommerce.shoes.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public ProductListResp productListService(ProductListReq productListReq){
        List<Product> productList=productRepository.findByProNameContaining(productListReq.getSearch());
        return ProductListResp.builder()
                .content(productList)
                .build();
    }

    @Override
    public Product productStoreService(ProductCreateReq productCreateReq, MultipartFile file) throws IOException {
        try {
            Product product=new Product();
            product.setProName(productCreateReq.getProName());
            product.setProDescription(product.getProDescription());
            Optional<Category> categoryOptional = categoryRepository.findById(productCreateReq.getCatId());
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
            } else {
                throw new RuntimeException("Categoría no encontrada");
            }
            product.setProUnitPrice(productCreateReq.getProUnitPrice());
            product.setProSizePlatform(productCreateReq.getProSizePlatform());
            product.setProSizeTacon(productCreateReq.getProSizeTacon());
            product.setProUrlImage(saveFile(file));
            return productRepository.save(product);

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while creating the product", e);
        }
    }

    @Override
    public Product productEditService(int id,ProductEditReq productEditReq, MultipartFile file){
        Optional<Product> productFind=productRepository.findById(id);
            productFind.get().setProName(productEditReq.getProName());
            productFind.get().setProDescription(productEditReq.getProDescription());
            Optional<Category> categoryOptional = categoryRepository.findById(productEditReq.getCatId());
            if (categoryOptional.isPresent()) {
                productFind.get().setCategory(categoryOptional.get());
            } else {
                throw new RuntimeException("Categoría no encontrada");
            }
            productFind.get().setProUnitPrice(productEditReq.getProUnitPrice());
            productFind.get().setProSizePlatform(productEditReq.getProSizePlatform());
            productFind.get().setProSizeTacon(productEditReq.getProSizeTacon());
            productFind.get().setProUrlImage(saveFile(file));
            return productRepository.save(productFind.get());
    }

    private void deleteFile(String fileName) {
        try {
            String uploadDir = "uploads";
            Path filePath = Paths.get(uploadDir).resolve(fileName);
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Error al eliminar el archivo: " + ex.getMessage());
        }
    }
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

            // Guardar la URL relativa de la imagen
            String fileUrl = "http://127.0.0.1:8080/product/images/" + fileName; // URL relativa

            return fileUrl; // Retornar la URL relativa de la imagen
        } catch (IOException ex) {
            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
        }
    }

//
//    private String saveFile(MultipartFile archivo) {
//        try {
//            String uploadDir = "uploads";
//            Path uploadPath = Paths.get(uploadDir);
//            if (!uploadPath.toFile().exists()) {
//                uploadPath.toFile().mkdirs();
//            }
//            String fileName = archivo.getOriginalFilename();
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(archivo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//            String fileAbsolutePath = filePath.toAbsolutePath().toString();
//            return fileAbsolutePath;
//        } catch (IOException ex) {
//            throw new RuntimeException("Error al guardar el archivo: " + ex.getMessage());
//        }
//    }

}
